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

	public Location findByPrimaryKey(int primaryKey) throws NoSuchRowException {
		System.out.println("BankManager.findByPrimaryKey :: primaryKey = " + primaryKey);
		Location obj = em.find(Location.class, primaryKey);
		if (obj == null) {
			throw new NoSuchRowException();
		} else
			return obj;
	}

	// public void save(Bank arg) {
	// 	Bank bank = em.find(Bank.class, arg.getBankId());
	// 	if (bank == null)
	// 		em.persist(arg);
	// 	else
	// 		em.merge(arg);
	// }

	// public void delete(int primaryKey) throws NoSuchRowException {
	// 	Object obj = em.find(Bank.class, primaryKey);
	// 	if (obj != null)
	// 		em.remove(obj);
	// 	else
	// 		throw new NoSuchRowException();
	// }

	@Override
	public void checkout() {
	}

}