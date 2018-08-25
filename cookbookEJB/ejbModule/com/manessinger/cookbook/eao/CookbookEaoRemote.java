package com.manessinger.cookbook.eao;

import java.util.List;

import javax.ejb.Remote;

import com.manessinger.cookbook.entity.Country;
import com.manessinger.cookbook.exception.EntityNotFoundException;
import com.manessinger.util.jpa.Entity;

@Remote
public interface CookbookEaoRemote {

	long countCountries();

	List<Country> allCountries();
    public <T extends Entity> T findOrFail(Class<T> clazz, Integer id) throws EntityNotFoundException;
    public <T extends Entity> void persist(T entity);	
    public Country countryByName(String name);
}