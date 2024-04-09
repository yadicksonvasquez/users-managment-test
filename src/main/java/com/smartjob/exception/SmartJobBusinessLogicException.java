/**
 * 
 */
package com.smartjob.exception;

/**
 * Error logica del negocio
 * 
 * @author yadickson
 */
public class SmartJobBusinessLogicException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SmartJobBusinessLogicException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public SmartJobBusinessLogicException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public SmartJobBusinessLogicException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SmartJobBusinessLogicException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SmartJobBusinessLogicException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
