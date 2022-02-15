package com.cognizant.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value="Stock Details", description = "A StockPrice Model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDetails {

	@ApiModelProperty(notes = "The database generated product ID")
	private String stockId;
	@ApiModelProperty(notes = "The name of a stock", required = true)
	private String stockName;
	@ApiModelProperty(notes = "The value of a stock", required = true)
	private double stockValue;
	
}
