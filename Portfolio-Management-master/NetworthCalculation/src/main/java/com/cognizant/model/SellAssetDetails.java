package com.cognizant.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value="SellAsset Details", description = "A SellAsset Model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellAssetDetails {

	@ApiModelProperty(notes = "The database generated product ID")
	private int id;
	@ApiModelProperty(notes = "The ID of a asset", required = true)
	private String assetId;
	@ApiModelProperty(notes = "The count of assets", required = true)
	private int assetcount;
}
