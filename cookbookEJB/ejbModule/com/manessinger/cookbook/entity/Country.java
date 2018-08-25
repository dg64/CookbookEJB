package com.manessinger.cookbook.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country extends com.manessinger.util.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COUNTRY_ID_GENERATOR", sequenceName="COUNTRY_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COUNTRY_ID_GENERATOR")
	private Integer id;

	private String name;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="country", fetch = FetchType.EAGER)
	private List<City> cities;

	//bi-directional many-to-one association to Zip
	@OneToMany(mappedBy="country", fetch = FetchType.EAGER)
	private List<Zip> zips;

	public Country() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setCountry(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setCountry(null);

		return city;
	}

	public List<Zip> getZips() {
		return this.zips;
	}

	public void setZips(List<Zip> zips) {
		this.zips = zips;
	}

	public Zip addZip(Zip zip) {
		getZips().add(zip);
		zip.setCountry(this);

		return zip;
	}

	public Zip removeZip(Zip zip) {
		getZips().remove(zip);
		zip.setCountry(null);

		return zip;
	}

}