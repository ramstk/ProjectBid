package com.intuit.cg.backendtechassessment.domain;

public class Bid implements Identifiable{
	private Long bid_ID;
	private Double bAmount;
	private Long buyID;
	private Long projID;
	
	public Long getId() {
		
		return bid_ID;
	}

	@Override
	public void setID(Long bid_ID) {
		
		this.bid_ID = bid_ID;

	}

	/**
	 * @return the bAmount
	 */
	public Double getbAmount() {
		return bAmount;
	}

	/**
	 * @param bAmount the bAmount to set
	 */
	public void setbAmount(Double bAmount) {
		this.bAmount = bAmount;
	}

	/**
	 * @return the buyID
	 */
	public Long getBuyID() {
		return buyID;
	}

	/**
	 * @param buyID the buyID to set
	 */
	public void setBuyID(Long buyID) {
		
		this.buyID = buyID;
	}

	/**
	 * @return the projID
	 */
	public Long getProjID() {
		return projID;
	}

	/**
	 * @param projID the projID to set
	 */
	public void setProjID(Long projID) {
		
		this.projID =  projID;
	}
	@Override
    public String toString() {
        return String.format("BID {ID=%s, bid-Amount=%s, Buyer-ID=%s, Proj-ID=%s}", bid_ID, bAmount, buyID, projID );
    }

}
