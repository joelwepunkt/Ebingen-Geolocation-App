

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.json.*;

import com.ebingengeolocation.entity.*;
import com.ebingengeolocation.session.LocationManager;
import com.ebingengeolocation.session.LocationManagerInterface;

import com.ebingengeolocation.session.NoSuchLocation;
import com.ebingengeolocation.session.NoteManagerInterface;
import com.ebingengeolocation.*;

public class Test {
	public final static String MODULE_NAME = "backend"; // = Name des
	// EJB-Jar-File
	public final static String BEAN_NAME_LOC = "LocationManager";
	public final static String BEAN_NAME_NOTE = "NoteManager";
	public final static String INTERFACE_NAME = "com.ebingengeolocation.session.";
	public final static String LOOKUP_NAME_LOC = MODULE_NAME + "/" + BEAN_NAME_LOC + "!" + INTERFACE_NAME;
	public final static String LOOKUP_NAME_NOTE = MODULE_NAME + "/" + BEAN_NAME_NOTE + "!" + INTERFACE_NAME;
// /////////////////////////
// neue Aufruf-Syntax: <jar-file-name>/<bean-name>!<interface-name>
// /////////////////////////
	
	

	public static void main(String[] args) throws NamingException {
		InitialContext ctx1 = null;
		InitialContext ctx2 = null;
		LocationManagerInterface m = null;
		NoteManagerInterface n = null;
		try {
			ctx1 = new InitialContext();
			m = (LocationManagerInterface) ctx1.lookup(LOOKUP_NAME_LOC + "LocationManagerInterface");
			ctx2 = new InitialContext();
			n = (NoteManagerInterface) ctx2.lookup(LOOKUP_NAME_NOTE + "NoteManagerInterface");
// /////////////////////////

			System.out.println("m.list() = " + m.list());
			System.out.println("m.findByLocationId(1) = " + m.findByLocationId(1));
			//m.getNearLocation(1,1);
			System.out.println("m.getNearLocation(48.210483, 9.030926) = " + m.getNearLocation(48.210483, 9.030926));
			System.out.println("n.list() = " + n.list());
			System.out.println("n.getNote(1) = " + n.getNote(1));
		} catch (NoSuchLocation e) {
			e.printStackTrace();

		}
		finally {}
	}
}
