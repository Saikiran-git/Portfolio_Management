package com.cognizant.dailymutualfundnav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.dailymutualfundnav.model.MutualFundDetails;

@Repository
public interface MutualFundRepository extends JpaRepository<MutualFundDetails,String>{

	public MutualFundDetails findByMutualFundName(String mutualFundName);

	public MutualFundDetails findByMutualFundId(String mutualFundId);
	@Query("select mutualFundValue from MutualFundDetails where mutualFundId=?1")
	public double findMutualFundValueById(String mutualFundId);
}
