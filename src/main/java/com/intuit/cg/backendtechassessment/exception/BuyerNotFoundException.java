/**
 * 
 */
package com.intuit.cg.backendtechassessment.exception;

/**
 * @author RAM
 *
 */
public class BuyerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@SuppressWarnings("unused")
	private Long resourceId;

	public BuyerNotFoundException(Long resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}

	public BuyerNotFoundException(String message) {
		super(message);

	}

}
