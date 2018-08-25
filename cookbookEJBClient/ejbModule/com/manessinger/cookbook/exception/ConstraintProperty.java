package com.manessinger.cookbook.exception;

import java.io.Serializable;

public class ConstraintProperty implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8204690417556956351L;
	
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
