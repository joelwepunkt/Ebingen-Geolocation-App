package com.ebingengeolocation.session;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.json.*;

import com.ebingengeolocation.entity.*;

@Stateless
@Remote(NoteManagerInterface.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NoteManager implements NoteManagerInterface, java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "locationpu")
	private EntityManager em;

	@Resource //(name = "java:/OracleDS")
	private SessionContext ejbContext;
	
	public NoteManager() {
	}
	
	public Collection<Note> list() {
		return em.createQuery("select n from Note n").getResultList();
	}
	
	public Note findByNoteId(int noteId) throws NoSuchNote {
		System.out.println("NoteManager.findByNoteId :: noteId = " + noteId);
		Note obj = em.find(Note.class, noteId);
		if (obj == null) {
			throw new NoSuchNote();
		} else
			return obj;
	}
	
	public void save(Note arg) {
		Note note = em.find(Note.class, arg.getNoteid());
		if (note == null)
			em.persist(arg);
		else
			em.merge(arg);
	}
	
	public void delete(int primaryKey) throws NoSuchNote {
		Object obj = em.find(Note.class, primaryKey);
		if (obj != null)
			em.remove(obj);
		else
			throw new NoSuchNote();
	}
	
	public String getNote(int locationid) {
		Query query = em.createQuery("select n from Note n "
				+ "where locationid = ?1", Note.class);
		query.setParameter(1, locationid);
		Collection<Note> note = query.getResultList();
		JSONArray notes = new JSONArray();
		Iterator iterator = note.iterator();
		while (iterator.hasNext()) {
			Note n = (Note) iterator.next();
			String json = "{\"locationId\": \"" + Integer.toString(n.getLocationid())+
					"\", \"commentary\": \"" + n.getCommentary() + "\" }";
			notes.put(new JSONObject(json));
		}
		System.out.println(notes.toString());
		return notes.toString();
	}
	
	public void setNote(int locationid, String commentary) {
		Query query = em.createQuery("insert into note values note_sequence.nextval, ?1, ?2");
		query.setParameter(1, locationid);
		query.setParameter(2, commentary);
		query.executeUpdate();
	}
	
	@Override
	public void checkout() {
	}
}


