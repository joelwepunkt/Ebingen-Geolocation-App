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
	
	public Collection<Location> getNearLocation(float x, float y) {
		return em.createQuery("select o from Location l where SDO_CONTAINS(l.SHAPES, SDO_GEOMETRY(2001,null, SDO_POINT_TYPE(" + x + "," + y+ ", null), null, null)) = 'TRUE'", Location.class).getResultList;
		//return em.createQuery("select o from Location l where SDO_CONTAINS(l.SHAPES, SDO_ANYINTERACT(l.SHAPES, SDO_GEOMETRY(2003,null, null, SDO_ELEM_INFO_ARRAY(1, 1003, 4), SDO_ORDINATE_ARRAY(" + x + "+3," + y +", " + x + "," + y + "+3, " + x + "-3," + y + "))) = 'TRUE'", Location.class).getResultList;
		}


	@Override
	public void checkout() {
	}

}