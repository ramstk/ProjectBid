/**
 * The unique ID for the domain objects
 */
package com.intuit.cg.backendtechassessment.domain;

/**
 * @author RAM
 *
 */

public interface Identifiable extends org.springframework.hateoas.Identifiable<Long> {

	public void setID(Long id);

}
