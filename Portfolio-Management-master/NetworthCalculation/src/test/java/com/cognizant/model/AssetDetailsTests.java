package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AssetDetailsTests {

	AssetDetails assetDetails = new AssetDetails();

	@Test
	void testSetTid() {
		assetDetails.setAssetId("AXIS");
		assertEquals("AXIS", assetDetails.getAssetId());
	}

	@Test
	void testSetAssetid() {
		assetDetails.setAssetId("Baroda");
		assertEquals("Baroda", assetDetails.getAssetId());
	}

	@Test
	void testSetPortfolioid() {
		assetDetails.setPortfolioId(101);
		assertEquals(101, assetDetails.getPortfolioId());
	}

	@Test
	void testSetType() {
		assetDetails.setAssettype("MutualFund");
		assertEquals("MutualFund", assetDetails.getAssettype());
	}

	@Test
	void testSetUnits() {
		assetDetails.setAssetcount(10);
		assertEquals(10, assetDetails.getAssetcount());
	}

	@Test
	void testAssetIntStringIntStringInt() {
		AssetDetails assetDetails = new AssetDetails(1, 101, "AXIS", "MutualFund", 10);
		assertNotNull(assetDetails);
	}

	@Test
	void testAsset() {
		AssetDetails assetDetails = new AssetDetails();
		assertNotNull(assetDetails);
	}

	@Test
	void testToString() {
		AssetDetails assetDetails = new AssetDetails(1, 101, "AXIS", "MutualFund", 10);
		assertEquals("AssetDetails(id=1, portfolioId=101, assetId=AXIS, assettype=MutualFund, assetcount=10)", assetDetails.toString());
	}


}
