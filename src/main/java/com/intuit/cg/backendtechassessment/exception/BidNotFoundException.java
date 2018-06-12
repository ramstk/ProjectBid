package com.intuit.cg.backendtechassessment.exception;

public class BidNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6834965614366486167L;
	@SuppressWarnings("unused")
	private Long resourceId;

	public BidNotFoundException(Long resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}

	public BidNotFoundException(String message) {
		super(message);

	}

}
