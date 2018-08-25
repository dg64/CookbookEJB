package com.manessinger.cookbook.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City extends com.manessinger.util.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CITY_ID_GENERATOR", sequenceName="CITY_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CITY_ID_GENERATOR")
	private Integer id;

	private String name;

	//bi-directional many-to-one association to Country
	@ManyToOne
	private Country country;

	public City() {
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

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}