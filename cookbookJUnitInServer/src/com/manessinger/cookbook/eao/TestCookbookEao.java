package com.manessinger.cookbook.eao;

import static org.junit.Assert.assertEquals;

import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.manessinger.junit.Util;

public class TestCookbookEao {
    private CookbookEaoRemote eao;

    @Before
    public void setUp() throws Exception {
        eao = (CookbookEaoRemote) new InitialContext(Util.getInitProperties())
                .lookup("java:global/cookbookEAR/cookbookEJB/CookbookEao"
                        + "!com.manessinger.cookbook.eao.CookbookEaoRemote");
    }

    @Test
    public void countryCountTest() {
        long n = eao.countCountries();
        assertEquals(3, n);
    }
}
