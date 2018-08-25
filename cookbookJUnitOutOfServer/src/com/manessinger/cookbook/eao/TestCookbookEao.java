package com.manessinger.cookbook.eao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.manessinger.junit.Util;

public class TestCookbookEao {
	
	private EntityManager _em;
	
	private CookbookEao eao;
	
	@Before
	public void setUp() throws Exception {
		_em = Util.getEntityManager();
		eao = new CookbookEao(_em);
	}
	
    @Test
    public void countryCountTest() {
        long n = eao.countCountries();
        assertEquals(3, n);
    }
}
