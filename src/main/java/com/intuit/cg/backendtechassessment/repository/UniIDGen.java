/**
 * This is used for generating unique IDs
 */
package com.intuit.cg.backendtechassessment.repository;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author RAM
 *
 */
// to allow the application to recognize the class as an injectable component
@Component
// to ensure that new object is created each time it is autowired
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UniIDGen {
	// By using automic long we use the built-in concurrency
	private AtomicLong nextId = new AtomicLong(1);

	public long getNextId() {
		return nextId.getAndIncrement();
	}

}
