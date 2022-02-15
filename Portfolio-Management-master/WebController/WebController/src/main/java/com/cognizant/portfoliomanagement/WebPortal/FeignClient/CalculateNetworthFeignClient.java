package com.cognizant.portfoliomanagement.WebPortal.FeignClient;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.portfoliomanagement.WebPortal.Model.Asset;
import com.cognizant.portfoliomanagement.WebPortal.Model.AssetDetails;
import com.cognizant.portfoliomanagement.WebPortal.Model.SellObjectMap;



@FeignClient(name="networthcalculation-service",url="http://localhost:8081" )
public interface CalculateNetworthFeignClient {
	
	@GetMapping("/GetAllAssets/{portfolioId}")
	public List<AssetDetails> getAllAssets(@RequestHeader("Authorization") String token,@PathVariable(value = "portfolioId") int portfolioId);
	
	@GetMapping("/calculateNetworth/{id}")
	public double calculateNetworth(@RequestHeader("Authorization") String token,@PathVariable(value = "id") int id);
	
	
	@PostMapping("/sellassets")
	public double sellPortfolioAssets(@RequestHeader("Authorization") String token,@RequestBody SellObjectMap sellObjectMap);
}
