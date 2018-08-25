package com.manessinger.cookbook.exception;

public class TransactionRollbackException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type;
	private String message;
	
	public TransactionRollbackException(Throwable t) {
		Throwable current = t;
		
		do {
			if (current.getCause() == null) {
				type = current.getClass().getSimpleName();
				message = current.getMessage();
			}
			current = current.getCause();
		} while (current != null);
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}
	
	
}
