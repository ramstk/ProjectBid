/**
 * Bean for displaying Buyer details with basic buyer information along with getters, setters and toString method
 */
package com.intuit.cg.backendtechassessment.domain;

/**
 * @author RAM
 *
 */
public class Buyer implements Identifiable {
	private Long bid;
	private String bName;

	public Long getId() {

		return bid;
	}

	@Override
	public void setID(Long bid) {

		this.bid = bid;

	}

	/**
	 * @return the bName
	 */
	public String getbName() {
		return bName;
	}

	/**
	 * @param sName
	 *            the sName to set
	 */
	public void setbName(String bName) {
		this.bName = bName;
	}

	@Override
	public String toString() {
		return String.format("Buyer {ID=%s, name=%s}", bid, bName);
	}

}
