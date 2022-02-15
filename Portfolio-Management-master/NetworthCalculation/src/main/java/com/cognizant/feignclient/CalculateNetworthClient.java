package com.cognizant.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "networthcalculation-service",url = "http://localhost:8081/")
public interface CalculateNetworthClient {

	@GetMapping("/calculateNetworth/{id}")
	public double calculateNetworth(@PathVariable(value = "id") int id,@RequestHeader("Authorization")String header) ;
}
