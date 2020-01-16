package com.ebingengeolocation.session;

import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.Remove;

import com.ebingengeolocation.entity.*;

@Remote
public interface NoteManagerInterface {
	
	public Collection<Note> list();
	
	public Note findByNoteId(int noteId) throws NoSuchNote;
	
	public String getNote(int locationid);
	
	public void setNote(int locationid, String commentary) throws NoSuchNote;

	public void delete(int primaryKey) throws NoSuchNote;

	public void save(Note arg);

	@Remove
	void checkout();
}
