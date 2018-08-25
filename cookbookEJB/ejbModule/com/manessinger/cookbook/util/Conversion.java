package com.manessinger.cookbook.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.manessinger.cookbook.dto.CountryDump;
import com.manessinger.cookbook.dto.ZipDto;
import com.manessinger.cookbook.eao.CookbookEaoRemote;
import com.manessinger.cookbook.entity.City;
import com.manessinger.cookbook.entity.Country;
import com.manessinger.cookbook.entity.Zip;
import com.manessinger.cookbook.exception.EntityNotFoundException;
import com.manessinger.util.jpa.Entity;

@Stateless
public class Conversion {
		@EJB CookbookEaoRemote eao;
		
		public Conversion() {
			
		}
		
		//For unit testing
		public Conversion(CookbookEaoRemote eao) {
			this.eao = eao;
		}

		public CountryDump fromEntity(Country e) {			
			CountryDump result = new CountryDump();
			result.setName(e.getName());
			List<String> cities = new ArrayList<String>();
			
			for(City city : e.getCities()) {
				cities.add(city.getName());
			}
			result.setCities(cities);
			return result;
		}
		
		public ZipDto fromEntity(Zip e) {
			ZipDto result = new ZipDto();
			result.setId(e.getId());
			result.setName(e.getName());
			result.setCode(e.getCode());
			result.setCountryId(e.getCountry().getId());
			return result;
		}
		
		public Zip fromDto(ZipDto d) throws EntityNotFoundException {
			Zip result;
			Integer id = d.getId();
			
			if (Entity.isId(id)) {
				result = eao.findOrFail(Zip.class, id);
			} else {
				result = new Zip();
			}
			result.setName(d.getName());
			result.setCode(d.getCode());
			result.setCountry(eao.findOrFail(Country.class, d.getCountryId()));
			return result;
		}
}
