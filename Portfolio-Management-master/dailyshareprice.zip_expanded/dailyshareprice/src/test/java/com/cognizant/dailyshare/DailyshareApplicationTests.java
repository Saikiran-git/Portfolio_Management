package com.cognizant.dailyshare;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.dailyshareprice.DailysharepriceApplication;
import com.cognizant.dailyshareprice.controller.StockController;
import com.cognizant.dailyshareprice.model.Stock;
import com.cognizant.dailyshareprice.repository.StockRepository;
import com.cognizant.dailyshareprice.service.StockService;


@AutoConfigureMockMvc
@SpringBootTest(classes = DailysharepriceApplication.class)
class DailyshareApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private StockController stockController;

	@Autowired
	private StockService stockService;

	@Autowired
	private StockRepository stockRepo;

	@Test
	void contextLoads() {
		assertNotNull(stockController);
	}

	@Test
	public void testgetAllDailySharePrice() throws Exception{
		ResultActions actions = mvc.perform(get("/dailyAllSharePrice"));
		actions.andExpect(status().isOk());
	}
	@Test
	public void testGetDailyShareById() throws Exception {
		ResultActions actions = mvc.perform(get("/dailyShareId/AMZ"));
		actions.andExpect(status().isOk());
		
	}
	@Test
	public void testGetDailySharePriceById() throws Exception {
		ResultActions actions = mvc.perform(get("/dailySharePriceById/AMZ"));
		actions.andExpect(status().isOk());
		
	}

	@Test
	public void testSetStockId() {
		Stock s = new Stock();
		s.setStockId("G");
		assertTrue(s.getStockId() == "G");
	}
	@Test
	public void testSetStockName() {
		Stock s = new Stock();
		s.setStockName("DLF");
		assertTrue(s.getStockName() == "DLF");
	}
	@Test
	public void testSetStockValue() {
		Stock s = new Stock();
		s.setStockValue(1900);
		assertTrue(s.getStockValue() == 1900);
	}
	@Test
	public void testSetStock() {
		Stock s = new Stock("S","Amazon",1700);
		assertTrue(s.getStockId() == "S");
	}
	@Test
	public void testSetStocktoString() {
		Stock s = new Stock("S","Amazon",1700);
		assertFalse(s.toString() == "G");
	}
	@Test
	public void testFindOne() throws Exception {
		Stock item = stockService.getStockByName("Amazon");
		boolean temp = item.getStockValue() == 1500.0 ? true : false;
		assertTrue(temp);
	}
	@Test
	public void testFindTwo() throws Exception {
		Stock item = stockService.getShareById("AMZ");
		boolean temp = item.getStockValue() == 1500.0 ? true : false;
		assertTrue(temp);
	}
	@Test
	public void testFindThree() throws Exception {
		double item = stockService.getSharePriceById("AMZ");
		boolean temp = item == 1500.0 ? true : false;
		assertTrue(temp);
	}


}
