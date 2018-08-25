package com.manessinger.cookbook.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the zip database table.
 * 
 */
@Entity
@NamedQuery(name="Zip.findAll", query="SELECT z FROM Zip z")
public class Zip extends com.manessinger.util.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ZIP_ID_GENERATOR", sequenceName="ZIP_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ZIP_ID_GENERATOR")
	private Integer id;

	private String code;

	private String name;

	//bi-directional many-to-one association to Country
	@ManyToOne
	private Country country;

	public Zip() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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