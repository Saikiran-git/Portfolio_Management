package com.cognizant.dailyshareprice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.dailyshareprice.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,String>{

	public List<Stock> findAll();
	public Stock findByStockName(String stockName);
	public Stock findByStockId(String stockId);
	@Query("select stockValue from Stock where stockId=?1")
	public Double findStockValueById(String stockId);
}
