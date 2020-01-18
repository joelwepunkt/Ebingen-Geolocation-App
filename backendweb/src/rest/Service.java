package rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ebingengeolocation.entity.Location;
import com.ebingengeolocation.entity.Note;
import com.ebingengeolocation.session.LocationManagerInterface;
import com.ebingengeolocation.session.NoSuchLocation;
import com.ebingengeolocation.session.NoSuchNote;
import com.ebingengeolocation.session.NoteManagerInterface;

import java.util.Collection;

@Path("/app")
@RequestScoped
public class Service implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public final static String MODULE_NAME = "java:global/backend"; // = Name des
	// EJB-Jar-File
	public final static String INTERFACE_NAME = "com.ebingengeolocation.session.";
	public final static String LOOKUP_NAME_LOC = MODULE_NAME + "/" + "LocationManager" + "!" + INTERFACE_NAME + "LocationManagerInterface";
	public final static String LOOKUP_NAME_NOTE = MODULE_NAME + "/" + "NoteManager" + "!" + INTERFACE_NAME + "NoteManagerInterface";

	private LocationManagerInterface locationInterface;
	private NoteManagerInterface noteInterface;
	
	private InitialContext ctx_loc;
	private InitialContext ctx_note;
	
	{
		try {
			ctx_loc = new InitialContext();
			ctx_note = new InitialContext();
			locationInterface = (LocationManagerInterface) ctx_loc.lookup(LOOKUP_NAME_LOC);
			noteInterface = (NoteManagerInterface) ctx_note.lookup(LOOKUP_NAME_NOTE);
		} catch (NamingException e) {
			System.out.println("Naming Exception nach lookup");
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/listLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String listLocation() throws NoSuchLocation {
		try {
			return locationInterface.list().toString();
		} catch (Exception e){
			throw new NoSuchLocation();
		}
	}
	
	@GET
	@Path("/listComment")
	@Produces(MediaType.TEXT_PLAIN)
	public String listNote() throws NoSuchLocation {
		try {
			return noteInterface.list().toString();
		} catch (Exception e){
			throw new NoSuchLocation();
		}
	}
	
	@GET
	@Path("/nearLoc/{x},{y}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getNearLocation(@PathParam("x") double x, @PathParam("y") double y) throws NoSuchLocation {
		try {
			return locationInterface.getNearLocation(x, y);
		} catch (Exception e){
			throw new NoSuchLocation();
		}
	}
	
	@GET
	@Path("/getComment/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getNote(@PathParam("id") int locationid) throws NoSuchNote {
		try {
			return noteInterface.getNote(locationid);
		} catch (Exception e){
			throw new NoSuchNote();
		}
	}
	
	@POST
	@Path("/saveComment")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveNote(JSONObject json) throws NoSuchNote {
		System.out.println("LocationId = " + Integer.toString(json.getInt("locationid")) + ", Commentary = " + json.get("commentary"));
		try {
			noteInterface.save(new Note(json.getInt("locationid"), json.getString("commentary")));
		} catch (Exception e) {
			throw new NoSuchNote();
		}
	}
	
	@POST
	@Path("/saveLocation")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveLocation(JSONObject json) throws NoSuchLocation {
		try {
			locationInterface.save(new Location(json.getDouble("lon"), json.getDouble("lat"), json.getString("title"), json.getString("description"), json.getString("adress")));
		} catch (Exception e) {
			throw new NoSuchLocation();
		}
	}
	
	@POST
	@Path("/deleteComment")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteNote(JSONObject json) throws NoSuchNote {
		try {
			noteInterface.delete(json.getInt("id"));
		} catch (Exception e) {
			throw new NoSuchNote();
		}
	}
	
	@POST
	@Path("/deleteLocation")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteLocation(JSONObject json) throws NoSuchLocation {
		try {
			noteInterface.delete(json.getInt("id"));
		} catch (Exception e) {
			throw new NoSuchLocation();
		}
	}

}
