package com.manessinger.junit;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Util {
	public static EntityManager getEntityManager( ) {
		return Persistence.createEntityManagerFactory("cookbookEJB_junit").createEntityManager();
	}

}
