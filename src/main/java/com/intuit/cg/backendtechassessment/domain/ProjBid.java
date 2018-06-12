/**
 * Bean for displaying Project and Bid 
 */
package com.intuit.cg.backendtechassessment.domain;
/**
 * @author RAM
 *
 */
public class ProjBid extends Project implements Identifiable {

	private String bName;
	private Double bAmount;

	/**
	 * @return the bName
	 */
	public String getbName() {
		return bName;
	}

	/**
	 * @param bName
	 *            the bName to set
	 */
	public void setbName(String bName) {
		this.bName = bName;
	}

	/**
	 * @return the bAmount
	 */
	public Double getbAmount() {
		return bAmount;
	}

	/**
	 * @param bAmount
	 *            the bAmount to set
	 */
	public void setbAmount(Double bAmount) {
		this.bAmount = bAmount;
	}

}
