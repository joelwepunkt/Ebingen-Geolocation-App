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
@Remote(LocationManagerInterface.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LocationManager implements LocationManagerInterface, java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "locationpu")
	private EntityManager em;

	@Resource //(name = "java:/OracleDS")
	private SessionContext ejbContext;

	public LocationManager() {
	}


	public Collection<Location> list() {
		return em.createQuery("select l from Location l").getResultList();
	}


	public Location findByLocationId(int locationId) throws NoSuchLocation {
		System.out.println("LocationManager.findByLocationId :: locationId = " + locationId);
		Location obj = em.find(Location.class, locationId);
		if (obj == null) {
			throw new NoSuchLocation();

		} else
			return obj;
	}

	public void save(Location arg) {
		Location Location = em.find(Location.class, arg.getLocationId());
		if (Location == null)
			em.persist(arg);
		else
			em.merge(arg);
	}

	public void delete(int primaryKey) throws NoSuchLocation {
		Object obj = em.find(Location.class, primaryKey);
		if (obj != null)
			em.remove(obj);
		else
			throw new NoSuchLocation();
	}
	
	public String getNearLocation(double x, double y) {
		Query query = em.createQuery("select l from Location l " 
				+ "where (sqrt(power(lon-?1, 2) + power(lat-?2, 2)) <= 0.005)", Location.class);
		query.setParameter(1, x);
		query.setParameter(2, y);
		Collection<Location> nearLoc = query.getResultList();
		System.out.println(nearLoc);
		JSONArray nearLocation = new JSONArray();
		Iterator iterator = nearLoc.iterator();
		while (iterator.hasNext()) {
			Location location = (Location) iterator.next();
			String json = "{\"locationId\": \"" + Integer.toString(location.getLocationId())+ 
				"\", \"longitude\": \"" + Double.toString(location.getLon()) + 
				"\", \"latitude\": \"" + Double.toString(location.getLat()) + 
				"\", \"title\": \"" + location.getTitle() + "\" }";
			nearLocation.put(new JSONObject(json));
		}

		System.out.println(nearLocation.toString());
		return nearLocation.toString();
	}


	@Override
	public void checkout() {
	}

}