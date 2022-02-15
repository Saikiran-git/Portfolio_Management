package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SellAssetDetailsTests {

	SellAssetDetails sellAssetDetails = new SellAssetDetails();

	@Test
	void testSellObjectMapIntMapOfStringIntegerMapOfStringInteger() {
		SellAssetDetails sellAssetDetails = new SellAssetDetails(101, "AXIS", 10);
		assertNotNull(sellAssetDetails);
		
	}

	@Test
	void testSetAssetId() {
		sellAssetDetails.setAssetId("AXIS");
		assertEquals("AXIS", sellAssetDetails.getAssetId());
	}

	@Test
	void testAssetCount() {
		sellAssetDetails.setAssetcount(10);
		assertEquals(10, sellAssetDetails.getAssetcount());
	}

	@Test
	void testIdEvaluation() {
		SellAssetDetails sellAssetDetails = new SellAssetDetails();
		assertNotNull(sellAssetDetails);
	}

	@Test
	void testToString() {
		SellAssetDetails sellAssetDetails = new SellAssetDetails(101,"AMZ",8);
		assertEquals("SellAssetDetails(id=101, assetId=AMZ, assetcount=8)", sellAssetDetails.toString());
	}

}
