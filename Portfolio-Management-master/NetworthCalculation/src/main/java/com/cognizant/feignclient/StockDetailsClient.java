package com.cognizant.feignclient;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.model.StockDetails;

@FeignClient(name="stock-service",url="http://localhost:8080/")
public interface StockDetailsClient {

	@GetMapping("/dailyAllSharePrice")
	public List<StockDetails> getAllDailySharePrice(HttpServletRequest req);
	
	@GetMapping("/dailySharePrice/{stockName}")
	public StockDetails getDailySharePrice(@PathVariable String stockName,HttpServletRequest req);
	
	@GetMapping("/dailyShareId/{stockId}")
	public StockDetails getDailyShareById(@PathVariable(value = "stockId") String stockId,HttpServletRequest req);
	
	@GetMapping("/dailySharePriceById/{stockId}")
	public double getDailySharePriceStock(@PathVariable("stockId") String stockId,@RequestHeader("Authorization")String header);
	
}
