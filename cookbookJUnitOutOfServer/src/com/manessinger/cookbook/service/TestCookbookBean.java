package com.manessinger.cookbook.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.manessinger.cookbook.dto.CountryDump;
import com.manessinger.cookbook.eao.CookbookEao;
import com.manessinger.cookbook.util.Conversion;
import com.manessinger.junit.Util;

public class TestCookbookBean {
	
	private EntityManager _em;
	private CookbookEao _eao;
	private Conversion _conv;
	
	private CookbookBean serviceBean;
	
	@Before
	public void setUp() throws Exception {
		_em = Util.getEntityManager();
		_eao = new CookbookEao(_em);
		_conv = new Conversion(_eao);
		
		serviceBean = new CookbookBean(_eao, _conv);
	}
	
   @Test
    public void countryCountTest() {
        long n = serviceBean.countryCount();
        assertEquals(3, n);
    }
 
    @Test
    public void dumpTest() {
        List<CountryDump> countries = serviceBean.dumpCountries();
        assertEquals(3, countries.size());
        for (CountryDump co : countries) {
            assertEquals(4, co.getCities().size());
        }
    }
	
}
