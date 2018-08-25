package com.manessinger.cookbook.exception;

public class EntityNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5209604977842920694L;
	
	private String className;
	private Integer id;
	
	public EntityNotFoundException(Class<?> clazz, Integer id) {
		super("Entity of class " + clazz.getSimpleName() + " with id " + id + " not found!");
		this.className = clazz.getSimpleName();
		this.id = id;
	}

	String getClassName() {
		return this.className;
	}
	
	Integer getId() {
		return this.id;
	}
}
