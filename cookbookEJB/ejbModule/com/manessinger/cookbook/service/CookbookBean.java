package com.manessinger.cookbook.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import com.manessinger.cookbook.dto.CountryDump;
import com.manessinger.cookbook.dto.ZipDto;
import com.manessinger.cookbook.eao.CookbookEaoRemote;
import com.manessinger.cookbook.entity.Country;
import com.manessinger.cookbook.entity.Zip;
import com.manessinger.cookbook.exception.EntityNotFoundException;
import com.manessinger.cookbook.exception.TransactionRollbackException;
import com.manessinger.cookbook.exception.ValidationException;
import com.manessinger.cookbook.util.Conversion;
import com.manessinger.cookbook.util.ValidationInterceptor;

/**
 * Session Bean implementation class CookbookBean
 */
@Stateless
@LocalBean
@WebService
public class CookbookBean implements CookbookBeanRemote {

	@EJB CookbookEaoRemote eao;
	@EJB Conversion conv;
	
    /**
     * Default constructor. 
     */
    public CookbookBean() {
        // TODO Auto-generated constructor stub
    }
    
    // For unit testing
    public CookbookBean(CookbookEaoRemote eao, Conversion conv) {
    	this.eao = eao;
    	this.conv = conv;
    }

	@Override
	public long countryCount() {
		// TODO Auto-generated method stub
		return eao.countCountries();
	}

	@Override	
	public List<CountryDump> dumpCountries() {
		// TODO Auto-generated method stub
		List<CountryDump> result = new ArrayList<CountryDump>();
		List<Country> allCountries = eao.allCountries();
		
		for(Country c : allCountries) {
			CountryDump ci = conv.fromEntity(c);
			result.add(ci);
		}
		return result;
	}

	@Override
	@Interceptors(ValidationInterceptor.class)
    public ZipDto storeZip(ZipDto in) 
    		throws EntityNotFoundException, ValidationException,  
					TransactionRollbackException {
		try {			
	        Zip zip = conv.fromDto(in);
	        eao.persist(zip);
	        return conv.fromEntity(zip);
		} catch (Throwable t) {
			throw new TransactionRollbackException(t);
		}
    }
}
