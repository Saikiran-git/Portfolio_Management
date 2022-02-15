package com.cognizant.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.model.MutualFundDetails;


@FeignClient(name = "dailymutualfundnav-service",url = "http://localhost:8090/")
public interface MutualFundDetailsClient {

	@GetMapping("/dailyAllMutualFundNav")
	public List<MutualFundDetails> getAllMutualFund();
	
	@GetMapping("/dailyMutualFundNavName/{mutualFundName}")
	public MutualFundDetails getDailyMutualFundNavName(@PathVariable String mutualFundName);
	
	@GetMapping("/dailyMutualFundNavId/{mutualFundId}")
	public MutualFundDetails getDailyMutualFundNavId(@PathVariable String mutualFundId);
	
	@GetMapping("/dailyMutualFundValueById/{mutualFundId}")
	public double getMutualFundValue(@PathVariable("mutualFundId") String mutualFundId);
	
	
}
