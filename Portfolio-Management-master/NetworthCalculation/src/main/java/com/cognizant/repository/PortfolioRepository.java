package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.model.PortfolioDetails;

public interface PortfolioRepository extends JpaRepository<PortfolioDetails, Integer> {

}
