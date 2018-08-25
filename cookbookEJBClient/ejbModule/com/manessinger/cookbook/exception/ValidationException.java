package com.manessinger.cookbook.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ViolationDetail> details = new ArrayList<ViolationDetail>();

	public ValidationException(List<ViolationDetail> details) {
		this.details = details;
	}

	public List<ViolationDetail> getDetails() {
		return details;
	}

}
