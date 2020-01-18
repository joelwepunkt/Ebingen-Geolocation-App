package com.ebingengeolocation.entity;

import javax.persistence.*;

@Entity
@Table(name = "note")
public class Note implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "noteKeyGenerator", sequenceName = "note_sequence", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noteKeyGenerator")
	@Column(name = "noteid")
	private int noteid;
	
	@Column(name = "locationid")
	private int locationid;
	
	@Column(name = "commentary")
	private String commentary;

	public int getNoteid() {
		return noteid;
	}

	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Note(int locationid, String commentary) {
		this.locationid = locationid;
		this.commentary = commentary;
	}
	
	public Note() {
		
	}
	
	@Override
	public String toString() {
		return "Note [noteId=" + noteid + ", locationId=" + locationid + ", commentary=" + commentary + "]";
	}
}
