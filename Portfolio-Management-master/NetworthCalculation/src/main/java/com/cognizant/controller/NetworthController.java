package com.cognizant.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.SellCountException;
import com.cognizant.feignclient.CalculateNetworthClient;
import com.cognizant.model.AssetDetails;
import com.cognizant.model.SellAssetDetails;
import com.cognizant.model.SellObjectMap;
import com.cognizant.repository.AssetRepository;
import com.cognizant.service.CalculateNetworth;

@Api(value = "/home", tags = { "Networth Controller" })
@RestController
@Slf4j
public class NetworthController {

	@Autowired
	AssetRepository assetRepository;

	@Autowired
	CalculateNetworth calculateNetworth;

	@Autowired
	CalculateNetworthClient calculateNetworthClient;

	@ApiOperation(value = "Returns details of Assets.", notes = "Details of a Stock is provided as JSON", response = AssetDetails.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Resource Not Found") })
	@GetMapping("/home")
	public List<AssetDetails> listAll(HttpServletRequest req) {
		List<AssetDetails> assetList = new ArrayList<AssetDetails>();
		String bearerToken = req.getHeader("Authorization");
		if (calculateNetworth.AuthorizeUser(bearerToken)) {
			assetList = assetRepository.findAll();

			for (AssetDetails a : assetList) {
				
				System.out.println(a);
			}
			return assetList;
		}
		
		return assetList;
	}

	@ApiOperation(value = "Returns Networth of a stakeholder bases on ID", notes = "Networth is provided as Double")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Resource Not Found") })
	@GetMapping("/calculateNetworth/{id}")
	public double calculateNetworth(
			@ApiParam(value = "ID of the Stakeholder", required = true) @PathVariable(value = "id") int id,
			HttpServletRequest req) {
		
		double netWorth = 0.0;
		System.out.println(id);
		String bearerToken = req.getHeader("Authorization");
		if (calculateNetworth.AuthorizeUser(bearerToken)) {

			List<AssetDetails> assets = calculateNetworth.getAllAssetForPortfolio(id);
			List<String> stockList = new ArrayList<>();
			List<String> mutualFundList = new ArrayList<>();
			
			for (AssetDetails assetDetails : assets) {
				if (assetDetails.getAssettype().equals("MutualFund")) {
					mutualFundList.add(assetDetails.getAssetId());
				} else {

					stockList.add(assetDetails.getAssetId());
				}
			}

			List<Double> stockPriceList = calculateNetworth.getSharePriceById(stockList, bearerToken);
			List<Double> mutualFundUnitsList = calculateNetworth.getMutualFundUnitsById(mutualFundList, bearerToken);
			int stock = 0;
			int mutualFund = 0;
			for (AssetDetails asset : assets) {
				if (asset.getAssettype().equals("Share")) {
					log.info("shareCount{}",asset.getAssetcount());
					log.info("shareName{}",asset.getAssetId());
					netWorth += asset.getAssetcount() * stockPriceList.get(stock);
					System.out.println(asset.getAssetId()+" "+"asset.getAssetcount() * stockPriceList.get(stock)="+asset.getAssetcount() * stockPriceList.get(stock));
					System.out.println(netWorth);
					stock++;
				} else {
					log.info("mutualFundCount{}",asset.getAssetcount());
					log.info("mutualFundName{}",asset.getAssetId());
					netWorth += asset.getAssetcount() * mutualFundUnitsList.get(mutualFund);
					System.out.println(asset.getAssetId()+" "+"asset.getAssetcount() * mutualFundUnitsList.get(stock)="+asset.getAssetcount() * mutualFundUnitsList.get(mutualFund));
					System.out.println(netWorth);
					mutualFund++;
				}
			}
			
			System.out.println(netWorth);
			return netWorth;
		}
		return netWorth;

	}

	@ApiOperation(value = "Returns Networth of a stakeholder bases on ID", notes = "Networth is provided as Double")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Resource Not Found") })
	@PostMapping("/sellassets")
	public double sellPortfolioAssets(
			@ApiParam(value = "Assest to be sold", required = true) @RequestBody SellObjectMap sellObjectMap,
			HttpServletRequest req) throws SellCountException {

		String bearerToken = req.getHeader("Authorization");
		if (calculateNetworth.AuthorizeUser(bearerToken)) {

			Map<String, Integer> stockIdList = sellObjectMap.getStockIdList();
			Map<String, Integer> mfIdList = sellObjectMap.getMfAssetList();
			SellAssetDetails sellAssetDetails=new SellAssetDetails();
			if (!stockIdList.isEmpty()) {
				System.out.println(sellObjectMap.getPid());
				sellAssetDetails.setId(sellObjectMap.getPid());
				for(String assetId:stockIdList.keySet())
				{
					sellAssetDetails.setAssetId(assetId);
					sellAssetDetails.setAssetcount(stockIdList.get(assetId));
					calculateNetworth.sellAsset(sellAssetDetails);
				}
			}
			SellAssetDetails sell=new SellAssetDetails();
			if (!mfIdList.isEmpty()) {
				sell.setId(sellObjectMap.getPid());
				for(String assetId:mfIdList.keySet())
				{
					sell.setAssetId(assetId);
					sell.setAssetcount(mfIdList.get(assetId));
					calculateNetworth.sellAsset(sell);
				}
			}
			System.out.println(sellAssetDetails.getId());
			double networth = calculateNetworthClient.calculateNetworth(sellAssetDetails.getId(),bearerToken );
			System.out.println(networth);

			return networth;
		}
		return 0;
	}
	@GetMapping("/GetAllAssets/{portfolioId}")
	public List<AssetDetails> getAllAssets(HttpServletRequest req,@PathVariable(value = "portfolioId") int portfolioId) {
		String bearerToken = req.getHeader("Authorization");
		
		if (calculateNetworth.AuthorizeUser(bearerToken)) {
			return calculateNetworth.getAllAssetForPortfolio(portfolioId);
		}
		return null;
	}
}
