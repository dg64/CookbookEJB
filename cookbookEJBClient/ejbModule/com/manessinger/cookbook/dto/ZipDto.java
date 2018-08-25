package com.manessinger.cookbook.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ZipDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7437020170561314169L;
	
	private Integer id;
	
	@Size(min=1, max=50)
	private String name;
	
	@Pattern(regexp="^(\\d{4,5}|\\d{5}-\\d{4})$")
	private String code;
	
	@Min(1)
	private Integer countryId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
}
