package com.intuit.cg.backendtechassessment.exception;

public class ProjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1244589248861456437L;
	@SuppressWarnings("unused")
	private Long resourceId;

	public ProjectNotFoundException(Long resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}

	public ProjectNotFoundException(String message) {
		super(message);

	}

}
