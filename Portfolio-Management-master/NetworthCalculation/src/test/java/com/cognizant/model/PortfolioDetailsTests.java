package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PortfolioDetailsTests {

	@Test
	void testPortfolio() {
		PortfolioDetails portfolio = new PortfolioDetails();
		assertNotNull(portfolio);
	}

	@Test
	void testPortfolioInt() {
		PortfolioDetails portfolio = new PortfolioDetails(1);
		assertNotNull(portfolio);
	}

	@Test
	void testToString() {
		PortfolioDetails portfolio = new PortfolioDetails(102);
		assertEquals("PortfolioDetails(portfolioId=102)", portfolio.toString());
	}

	@Test
	void testSetPortfolioId() {
		PortfolioDetails portfolio = new PortfolioDetails();
		portfolio.setPortfolioId(1);
		assertEquals(1, portfolio.getPortfolioId());
	}

}
