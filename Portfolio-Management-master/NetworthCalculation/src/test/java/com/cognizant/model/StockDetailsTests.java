package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StockDetailsTests {

	StockDetails stockDetails=new StockDetails();
	
	@Test
	void testGetStockId() {
		stockDetails.setStockId("DEL");
		assertEquals("DEL", stockDetails.getStockId());
	}

	@Test
	void testGetShareName() {
		stockDetails.setStockName("Delloite");
		assertEquals("Delloite", stockDetails.getStockName());
	}

	@Test
	void testGetShareValue() {
		stockDetails.setStockValue(2500.0);
		assertEquals(2500.0, stockDetails.getStockValue());
	}
	
	@Test
	void testStockDetailsStringStringDouble() {
		StockDetails stockDetails=new StockDetails("DEL", "Delloite", 2500.0);
		assertEquals("DEL", stockDetails.getStockId());
		assertEquals("Delloite", stockDetails.getStockName());
		assertEquals( 2500.0, stockDetails.getStockValue());
	}

	@Test
	void testStockDetails() {
		StockDetails stockDetails=new StockDetails();
	}

	@Test
	void testToString() {
		StockDetails stockDetails=new StockDetails("DEL", "Delloite", 2500.0);
		assertEquals("StockDetails(stockId=DEL, stockName=Delloite, stockValue=2500.0)", stockDetails.toString());
		
	}

}
