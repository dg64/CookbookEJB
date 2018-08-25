package com.manessinger.cookbook.service;

import javax.ejb.Remote;
import com.manessinger.cookbook.dto.CountryDump;
import com.manessinger.cookbook.dto.ZipDto;
import com.manessinger.cookbook.exception.EntityNotFoundException;
import com.manessinger.cookbook.exception.TransactionRollbackException;
import com.manessinger.cookbook.exception.ValidationException;

import java.util.List;

@Remote
public interface CookbookBeanRemote {
	public long countryCount();
	public List<CountryDump> dumpCountries();
	public ZipDto storeZip(ZipDto in) throws EntityNotFoundException, ValidationException, TransactionRollbackException ;
}
