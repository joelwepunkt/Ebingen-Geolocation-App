package com.ebingengeolocation.session;

import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.Remove;

import com.ebingengeolocation.entity.*;

@Remote
public interface LocationManagerInterface {

	public Collection<Location> list();

	public Location findByPrimaryKey(int primaryKey) throws NoSuchRowException;

	// public void delete(int primaryKey) throws NoSuchRowException;

	// public void save(Location arg);

	@Remove
	void checkout();
}