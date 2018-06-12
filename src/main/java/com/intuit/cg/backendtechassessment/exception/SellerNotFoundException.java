package com.intuit.cg.backendtechassessment.exception;

public class SellerNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9091745933279729673L;
	@SuppressWarnings("unused")
	private Long resourceId;

	public SellerNotFoundException(Long resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}

	public SellerNotFoundException(String message) {
		super(message);

	}

}
