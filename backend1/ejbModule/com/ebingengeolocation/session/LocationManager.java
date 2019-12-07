package com.ebingengeolocation.session;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		return em.createQuery("select o from Location o").getResultList();
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

	@Override
	public void checkout() {
	}

}