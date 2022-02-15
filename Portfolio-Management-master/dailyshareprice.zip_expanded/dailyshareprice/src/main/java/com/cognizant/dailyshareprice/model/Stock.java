package com.cognizant.dailyshareprice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@ApiModel(value="Stock Details", description = "A StockPrice Model")
@Entity
@Table(name="stockdetails")
public class Stock {
	

	@Id
	@Column(name="stock_id")
	@ApiModelProperty(notes = "The database generated product ID")
	private String stockId;
	
	@Column(name="stock_name")
	@ApiModelProperty(notes = "The name of a stock", required = true)
	private String stockName;
	
	@Column(name="stock_value")
	@ApiModelProperty(notes = "The value of a stock", required = true)
	private double stockValue;
	
	

	
	

			
}
