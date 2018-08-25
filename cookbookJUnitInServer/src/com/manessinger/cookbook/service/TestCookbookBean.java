package com.manessinger.cookbook.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.manessinger.cookbook.dto.CountryDump;
import com.manessinger.junit.Util;

public class TestCookbookBean {
	private CookbookBeanRemote serviceBean;
	
	@Before
	public void setup() throws Exception {
		serviceBean = 
				(CookbookBeanRemote) new InitialContext(Util.getInitProperties())
					.lookup("java:global/cookbookEAR/cookbookEJB/CookbookBean"
						+"!com.manessinger.cookbook.service.CookbookBeanRemote");
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
		for(CountryDump co : countries) {
			assertEquals(4, co.getCities().size());
		}
	}
	
}
