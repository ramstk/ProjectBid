/**
 * 
 */
package com.intuit.cg.backendtechassessment.domain;

/**
 * @author RAM
 *
 */
public class Seller implements Identifiable {
	private Long sid;
	private String sName;

	public Long getId() {

		return sid;
	}

	@Override
	public void setID(Long sid) {

		this.sid = sid;

	}

	/**
	 * @return the sName
	 */
	public String getsName() {
		return sName;
	}

	/**
	 * @param sName
	 *            the sName to set
	 */
	public void setsName(String sName) {
		this.sName = sName;
	}

	@Override
	public String toString() {
		return String.format("Seller {ID=%s, name=%s}", sid, sName);
	}

}
