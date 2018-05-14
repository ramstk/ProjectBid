/**
 * 
 */
package com.intuit.cg.backendtechassessment.domain;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;




/**
 * @author RAM
 *
 */

public class Project implements Identifiable {
	
	private Long pid;
	@NotNull
    @NotBlank
	private String pName;
	@NotNull
    @NotBlank
	private String pDesc;
	@NotNull
    @NotBlank
	private double maxBudget;
	@NotNull
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate pTimeLimit;	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	@NotNull
    @NotBlank
	private Long sID;
	private String pStatus;
	/**
	 * @return the pid
	 */
	@Override
	@Valid
	public Long getId() {
		
		return pid;
	}

	@Override
	public void setID(Long pid) {
		
		this.pid = pid;

	}

	/**
	 * @return the pName
	 */
	public String getpName() {
		return pName;
	}

	/**
	 * @param pName the pName to set
	 */
	public void setpName(String pName) {
		this.pName = pName;
	}

	/**
	 * @return the pDesc
	 */
	public String getpDesc() {
		return pDesc;
	}

	/**
	 * @param pDesc the pDesc to set
	 */
	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}

	/**
	 * @return the maxBudget
	 */
	public double getMaxBudget() {
		return maxBudget;
	}

	/**
	 * @param maxBudget the maxBudget to set
	 */
	public void setMaxBudget(double maxBudget) {
		this.maxBudget = maxBudget;
	}
	@NotNull @NotBlank @JsonFormat(pattern = "yyyy-MM-dd")
	/**
	 * @return the pTimeLimit
	 */
	public String getpTimeLimit() {
		return pTimeLimit.format(formatter);
	}

	/**
	 * @param pTimeLimit the pTimeLimit to set
	 */
	public void setpTimeLimit(String pTimeLimit) {
		this.pTimeLimit = LocalDate.parse(pTimeLimit,formatter);
	}

	/**
	 * @return the sID
	 */
	public long getsID() {
		return sID;
	}

	/**
	 * @param sID the sID to set
	 */
	public void setsID(long sID) {
		
		this.sID = sID;
	}

	/**
	 * @return the pstatus
	 */
	public String getpStatus() {
		
		return pStatus;
	}

	/**
	 * @param pStatus the pStatus to set
	 */
	public void setpStatus(String pstatus) {
		this.pStatus = pstatus;
	}
	


}
