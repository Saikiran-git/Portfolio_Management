package com.cognizant.dailyshareprice.controller;

import com.cognizant.dailyshareprice.exception.StockNotFoundException;
import com.cognizant.dailyshareprice.model.Stock;
import com.cognizant.dailyshareprice.service.StockService;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value="/dailySharePrice", tags={"SharePrice Controller"})
@RestController
public class StockController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

	@Autowired
	StockService service;

	@ApiOperation(value = "Returns details of all Stock.", notes="Details of a Stock is provided as JSON",
	response = Stock.class, responseContainer = "List")
	@ApiResponses(value = {@ApiResponse(code=404, message="Resource Not Found")})
	@GetMapping("/dailyAllSharePrice")
	public List<Stock> getAllDailySharePrice(HttpServletRequest req) throws StockNotFoundException{
		LOGGER.info("Start");
		String BearerToken = req.getHeader("Authorization");
		if(service.AuthorizeUser(BearerToken)){
			return service.getAllShares();
		}
		LOGGER.info("End");
		return null;
	}

	@ApiOperation(value = "Returns details of Stock based on Stock name.", notes="Details of a Stock is provided as JSON",
			response = Stock.class)
	@ApiResponses(value = {@ApiResponse(code=404, message="Resource Not Found")})
	@GetMapping("/dailySharePrice/{stockName}")
	public Stock getDailySharePrice(@ApiParam(value="Name of the stock", required = true) @PathVariable String stockName,HttpServletRequest req) throws StockNotFoundException{
		LOGGER.info("Start");
		String BearerToken = req.getHeader("Authorization");
		if(service.AuthorizeUser(BearerToken)){
			return service.getStockByName(stockName);
		}
		LOGGER.info("End");
		return null;
	}

	@ApiOperation(value = "Returns details of a Stock based on Stock Id.", notes="Details of a Stock is provided as JSON",
			response = Stock.class)
	@ApiResponses(value = {@ApiResponse(code=404, message="Resource Not Found")})
	@GetMapping("/dailyShareId/{stockId}")
	public Stock getDailyShareById(@ApiParam(value="ID of the stock", required = true) @PathVariable(value="stockId") String stockId,HttpServletRequest req) throws StockNotFoundException{
		LOGGER.info("Start");
		String BearerToken = req.getHeader("Authorization");
		if(service.AuthorizeUser(BearerToken)){
			return service.getShareById(stockId);
		}
		LOGGER.info("End");
		return null;
	}

	@ApiOperation(value = "Returns daily price of a Stock.", notes="Price of a Stock is provided as double",
			response = Stock.class, responseContainer = "List")
	@ApiResponses(value = {@ApiResponse(code=404, message="Resource Not Found")})
	@GetMapping("/dailySharePriceById/{stockId}")
	public double getDailySharePriceStock(@ApiParam(value="ID of the stock", required = true) @PathVariable("stockId") String stockId,HttpServletRequest req) throws StockNotFoundException{
		//System.out.println(stockId);
		//System.out.println(service.getSharePriceById(stockId));
		LOGGER.info("Start");
		System.out.println("start daily share price");
		String BearerToken = req.getHeader("Authorization");
		//if(service.AuthorizeUser(BearerToken)){
			System.out.println("success");
			System.out.println(stockId);
			System.out.println(service.getSharePriceById(stockId));
			return service.getSharePriceById(stockId);
	}
	
}
