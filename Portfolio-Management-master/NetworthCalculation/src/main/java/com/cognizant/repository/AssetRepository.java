package com.cognizant.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cognizant.model.AssetDetails;

@EnableJpaRepositories
public interface AssetRepository extends JpaRepository<AssetDetails, Integer> {

	//@Query("FROM Asset WHERE portfolioId = ?1")
	//public List<AssetDetails> findAssetUsingPortfolioId(int portfolioId);


	public List<AssetDetails> findByPortfolioIdOrderByAssetId(int portfolioId);

	@Query("FROM AssetDetails WHERE portfolioId = ?1")
	public List<AssetDetails> findAssetDetailsUsingPortfolioId(int portfolioId);

	public AssetDetails findByPortfolioIdAndAssetId(int portfolioId, String assetId);
	@Modifying
	@Query(value="Delete from AssetDetails where assetid =:assetId and portfolioId=:portfolioId")
	public void deleteByPortfolioIdAndAssetId(int portfolioId, String assetId);


	//@Query
	//public AssetDetails findByPortfolioIdAndAssetIdAndType(int portfolioId, String id, String string);
}
