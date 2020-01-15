package com.ebingengeolocation.entity;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "locationKeyGenerator", sequenceName = "location_sequence", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locationKeyGenerator")
	@Column(name = "locationID")
	private int locationId;
	
	@Column(name = "lon")
	private double lon;
	
	@Column(name = "lat")
	private double lat;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public double getLon() {
		return lon;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public double getLat() {
		return lat;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Location(int locationId, double lon, double lat, String title, String description, String address) {
		this.locationId = locationId;
		this.lon = lon;
		this.lat = lat;
		this.title = title;
		this.description = description;
		this.address = address;
	}
	public Location() {
		
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", longitude=" + lon + ", latitude=" + lat + ", title=" + title + ", description=" + description + ", address="
				+ address + "]";
	}
}