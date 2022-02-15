package com.cognizant.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.controller.NetworthController;
import com.cognizant.exception.SellCountException;
import com.cognizant.feignclient.MutualFundDetailsClient;
import com.cognizant.feignclient.StockDetailsClient;
import com.cognizant.model.MutualFundDetails;
import com.cognizant.model.StockDetails;
import com.cognizant.service.CalculateNetworth;

@SpringBootTest
@AutoConfigureMockMvc
class NetworthCalculationApplicationTests {

	/*@Autowired
    private NetworthController networthController;
	
	@Autowired
	private StockDetails stockDetails;
	
	@Autowired
	private MutualFundDetails mutualFundDetails;
	
CalculateNetworth calculateNetworth=new CalculateNetworth();
	
	@Autowired
	StockDetailsClient stockDetailsClient;
	
	@Autowired
	MutualFundDetailsClient mutualFundDetailsClient;

 

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void contextLoads() {
        //assertNotNull(networthController);
    }
    
    @Test
	void testCalculateNetworthGetNetworth() throws Exception {
    	
		ResultActions actions = mockMvc.perform(get("/calculateNetworth/101"));
		actions.andExpect(status().isMethodNotAllowed());
		//stockDetails.

	}


//	@Test
//	void testCalculateNetworthSellAssets() throws Exception {
//		ResultActions actions = mockMvc.perform(get("/sellassets"));
//		actions.andExpect(status().isNotFound());
//	}
	
	@Test
	public void testgetSharePriceById() {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		String bearerToken = mockRequest.getHeader("Authorization");
		List<String> stockIdList = Arrays.asList("DEL", "AMZ");
		//when(calculateNetworth.AuthorizeUser(bearerToken)).thenReturn(true);
		List<Double> stockValueList = calculateNetworth.getSharePriceById(stockIdList, bearerToken);
		assertNotEquals(0,stockValueList.size());
	}
	
	@Test
	public void testgetMutualFundUnitsById() {
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		String bearerToken = mockRequest.getHeader("Authorization");
		List<String> mutualFundList = Arrays.asList("AXIS","Baroda");
		//when(calculateNetworth.AuthorizeUser(bearerToken)).thenReturn(true);
		List<Double> mutualFundUnitsList = calculateNetworth.getMutualFundUnitsById(mutualFundList, bearerToken);
		assertNotEquals(0,mutualFundUnitsList);
	}
	
	@Test
	public void sellAsset() throws SellCountException {
		
		
	}*/

}
