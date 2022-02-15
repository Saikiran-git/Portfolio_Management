package com.cognizant.dailymutualfundnav.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dailymutualfundnav.exception.MutualFundNotFoundException;
import com.cognizant.dailymutualfundnav.model.MutualFundDetails;
import com.cognizant.dailymutualfundnav.service.MutualFundService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Swe
 *
 */
@Api(value = "/dailyAllMutualFundNav", tags = { "MutualFund Controller" })
@RestController
public class MutualFundController {

	@Autowired
	MutualFundService service;

	/**
	 * @return
	 */
	@ApiOperation(value = "Returns details of all MutualFund.", notes = "Details of a MutualFund is provided as JSON", response = MutualFundDetails.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Resource Not Found") })
	@GetMapping("/dailyAllMutualFundNav")
	public List<MutualFundDetails> getAllMutualFund() {

		return service.getAllMutualFund();
	}

	/**
	 * @param mutualFundName
	 * @return
	 * @throws MutualFundNotFoundException
	 */
	@ApiOperation(value = "Returns details of a MutualFund based on Name", notes = "Details of a MutualFund is provided as JSON", response = MutualFundDetails.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Resource Not Found") })
	
	@GetMapping("/dailyMutualFundNavName/{mutualFundName}")
	public MutualFundDetails getDailyMutualFundNavName(
			@ApiParam(value = "Name of a MutualFund", required = true) @PathVariable String mutualFundName)
			throws MutualFundNotFoundException {

		return service.getMutualFundByName(mutualFundName);
	}

	/**
	 * @param mutualFundId
	 * @return
	 * @throws MutualFundNotFoundException
	 */
	@ApiOperation(value = "Returns details of a MutualFund based on ID", notes = "Details of a MutualFund is provided as JSON", response = MutualFundDetails.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Resource Not Found") })
	@GetMapping("/dailyMutualFundNavId/{mutualFundId}")
	public MutualFundDetails getDailyMutualFundNavId(
			@ApiParam(value = "ID of a MutualFund", required = true) @PathVariable String mutualFundId)
			throws MutualFundNotFoundException {

		return service.getMutualFundById(mutualFundId);
	}

	/**
	 * @param mutualFundId
	 * @return
	 * @throws MutualFundNotFoundException
	 */
	@ApiOperation(value = "Returns daily price of a MutualFund based on ID", notes = "Details of a MutualFund is provided as Double", response = MutualFundDetails.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Resource Not Found") })
	@GetMapping("/dailyMutualFundValueById/{mutualFundId}")
	public double getMutualFundValue(
			@ApiParam(value = "ID of a MutualFund", required = true) @PathVariable("mutualFundId") String mutualFundId)
			throws MutualFundNotFoundException {
		System.out.println(mutualFundId);
		System.out.println(service.getMutualFundValueById(mutualFundId));
		return service.getMutualFundValueById(mutualFundId);
	}
}
