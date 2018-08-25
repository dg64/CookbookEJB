package com.manessinger.cookbook.eao;

import java.util.List;

import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.manessinger.cookbook.entity.Country;
import com.manessinger.cookbook.exception.EntityNotFoundException;
import com.manessinger.util.jpa.Entity;

/**
 * Session Bean implementation class CookbookEao
 */
@Stateless
@LocalBean
public class CookbookEao implements CookbookEaoRemote  {
	
	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public CookbookEao() {
        // TODO Auto-generated constructor stub
    }
    
    // For unit testing
    public CookbookEao(EntityManager em) {
    	this.em = em;
    }
    
    /* (non-Javadoc)
	 * @see com.manessinger.cookbook.eao.CookbookEaoRemote#countCountries()
	 */
    
	/* (non-Javadoc)
	 * @see com.manessinger.cookbook.eao.CookbookEaoRemote#countCountries()
	 */
	@Override
	public long countCountries() {
    	long result;
    	Query q = em.createQuery("Select count(co) From Country co");
    	result = (Long)q.getSingleResult();
    	return result;
    }
    
    /* (non-Javadoc)
	 * @see com.manessinger.cookbook.eao.CookbookEaoRemote#allCountries()
	 */
    
	/* (non-Javadoc)
	 * @see com.manessinger.cookbook.eao.CookbookEaoRemote#allCountries()
	 */
	@Override
	@SuppressWarnings("unchecked")
    public List<Country> allCountries() {
    	List<Country> result;
    	Query q = em.createQuery("Select co From Country co");
    	result = (List<Country>) q.getResultList();
    	return result;
    } 
	
    @Override
    public <T extends Entity> T findOrFail(Class<T> clazz, Integer id) throws EntityNotFoundException {
        T e = em.find(clazz, id);
        if (e == null) {
            throw new EntityNotFoundException(clazz.getClass(), id);
        }
        return e;
    }

    @Override
    public <T extends Entity> void persist(T entity) {
        if (entity.hasId()) {
            em.merge(entity);
        } else {
            em.persist(entity);
            //if (entity.getId() == null) {
                em.flush();
            //}
        }
    }
	
	@Override
	public Country countryByName(String name) {
		Country result;
		Query q = em.createQuery("Select co From Country co Where co.name = :name");
		q.setParameter("name", name);
		result = (Country) q.getSingleResult();
		return result;
	}

}
