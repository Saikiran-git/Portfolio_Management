package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MutualFundDetailsTests {

	MutualFundDetails mutualFundDetails=new MutualFundDetails();
	@Test
	void testSetMutualFundId() {
		mutualFundDetails.setMutualFundId("AXIS");
		assertEquals("AXIS", mutualFundDetails.getMutualFundId());
	}

	@Test
	void testSetMutualFundName() {
		mutualFundDetails.setMutualFundName("AxisMutualFund");
		assertEquals("AxisMutualFund", mutualFundDetails.getMutualFundName());
	}

	@Test
	void testSetMutualFundValue() {
		mutualFundDetails.setMutualFundValue(6000.0);
		assertEquals(6000.0, mutualFundDetails.getMutualFundValue());
	}

	@Test
	void testMutualFundDetailsStringStringDouble() {
		MutualFundDetails mutualFundDetails=new MutualFundDetails("AXIS", "AxisMutualFund", 6000.0);
	}

	@Test
	void testMutualFundDetails() {
		MutualFundDetails mutualFundDetails=new MutualFundDetails();
	}

	@Test
	void testToString() {
		MutualFundDetails mutualFundDetails=new MutualFundDetails("AXIS", "AxisMutualFund", 6000.0);
		assertEquals("MutualFundDetails(mutualFundId=AXIS, mutualFundName=AxisMutualFund, mutualFundValue=6000.0)", mutualFundDetails.toString());
	}

}
