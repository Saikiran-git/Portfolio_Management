package com.cognizant.dailymutualfundnav.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dailymutualfundnav.exception.MutualFundNotFoundException;
import com.cognizant.dailymutualfundnav.model.MutualFundDetails;
import com.cognizant.dailymutualfundnav.repository.MutualFundRepository;

@Service
public class MutualFundService {

	@Autowired
	MutualFundRepository repository;
	
	@Transactional
	public List<MutualFundDetails> getAllMutualFund(){
		return repository.findAll();
	}
	
	@Transactional
	public MutualFundDetails getMutualFundByName(String mutualFundName) throws MutualFundNotFoundException{
		if(repository.findByMutualFundName(mutualFundName)==null)
		{
			throw new MutualFundNotFoundException("Mutual Fund Not Found");
	}
		return repository.findByMutualFundName(mutualFundName);
	}
	
	@Transactional
	public MutualFundDetails getMutualFundById(String mutualFundId) throws MutualFundNotFoundException {
		 
		if(repository.findByMutualFundId(mutualFundId)==null)
		{
			
			throw new MutualFundNotFoundException("Mutual Fund Not Found");
	}
		return repository.findByMutualFundId(mutualFundId);
	}

	public double getMutualFundValueById(String mutualFundId) {
			System.out.println(mutualFundId);
		return repository.findMutualFundValueById(mutualFundId);
	}
	
	
	
}
