/**
 * To ensure that multiple id calls do not interfere
 */
package com.intuit.cg.backendtechassessment.test.unitTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.intuit.cg.backendtechassessment.repository.UniIDGen;


/**
 * @author RAM
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UniqueIDGeneratorTest {

	@Autowired
	private UniIDGen generator1;
	
	@Autowired
	private UniIDGen generator2;
	
	@Test
	public void testMultipleGeneratorsEnsureGeneratorsDoNotInterfere() throws Exception {
		Assert.assertEquals(1, generator1.getNextId());
		Assert.assertEquals(2, generator1.getNextId());
		Assert.assertEquals(1, generator2.getNextId());
		Assert.assertEquals(2, generator2.getNextId());
	}
}
