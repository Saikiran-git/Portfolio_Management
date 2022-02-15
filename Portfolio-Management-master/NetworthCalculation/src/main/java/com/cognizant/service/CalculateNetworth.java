package com.cognizant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.exception.SellCountException;
import com.cognizant.feignclient.AuthClient;
import com.cognizant.feignclient.MutualFundDetailsClient;
import com.cognizant.feignclient.StockDetailsClient;
import com.cognizant.model.AssetDetails;
import com.cognizant.model.SellAssetDetails;
import com.cognizant.model.SellObjectMap;
import com.cognizant.repository.AssetRepository;
import com.netflix.discovery.EurekaClient;

@Service
public class CalculateNetworth {

	@Autowired
	AssetRepository assetRepository;

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	AuthClient authClient;

	@Autowired
	StockDetailsClient stockDetailsClient;

	@Autowired
	MutualFundDetailsClient mutualFundDetailsClient;

	public Boolean AuthorizeUser(String Header) {
		try {
			return authClient.verify(Header);
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public List<AssetDetails> getAllAssetForPortfolio(int id) {
		List<AssetDetails> assetDetails=assetRepository.findByPortfolioIdOrderByAssetId(id);
		return assetDetails;
		
	}

	public List<Double> getSharePriceById(List<String> stockList, String header) {
		List<Double> stockValueList = new ArrayList<>();
		for (String stockId : stockList) {
			try {
				stockValueList.add(stockDetailsClient.getDailySharePriceStock(stockId, header));
			} catch (Exception e) {

				System.out.println("\n"+e+"\n");

			}
		}
		return stockValueList;
	}

	public List<Double> getMutualFundUnitsById(List<String> mutualFundList, String header) {
		

		List<Double> mutualFundUnitsList = new ArrayList<>();
		for (String mutualFundId : mutualFundList) {
			mutualFundUnitsList.add(mutualFundDetailsClient.getMutualFundValue(mutualFundId));
		}
		return mutualFundUnitsList;
	}
	@Transactional
	public void sellAsset(SellAssetDetails sellAssetDetails) throws SellCountException {

		int portfolioId = sellAssetDetails.getId();
		String assetId = sellAssetDetails.getAssetId();
		var assetDetails = assetRepository.findByPortfolioIdAndAssetId(portfolioId, assetId);
		int count = assetDetails.getAssetcount();
		int sellCount = sellAssetDetails.getAssetcount();
		if (sellCount > count) {
			throw new SellCountException("Sell count should be less than the available count");
		}
		if (count == sellCount) {
			assetRepository.deleteByPortfolioIdAndAssetId(portfolioId, assetId);
		} else {
			count -= sellCount;
		}
		assetDetails.setAssetcount(count);
		assetRepository.save(assetDetails);
	}

}
