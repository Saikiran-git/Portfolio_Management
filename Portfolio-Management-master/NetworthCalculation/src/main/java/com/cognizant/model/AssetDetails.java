package com.cognizant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value="Asset Details", description = "A AssetDetails Model")
@Entity
@Table(name = "assetdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetDetails {

	@Id
	@Column(name = "id")
	@ApiModelProperty(notes = "The database generated product ID")
	private int id;
	@Column(name = "portfolioid")
	@ApiModelProperty(notes = "The ID of a portfolio", required = true)
	private int portfolioId;
	@Column(name = "assetid")
	@ApiModelProperty(notes = "The ID of a Asset", required = true)
	private String assetId;
	@Column(name = "assettype")
	@ApiModelProperty(notes = "The type of a asset", required = true)
	private String assettype;
	@Column(name = "assetcount")
	@ApiModelProperty(notes = "The count of a Asset", required = true)
	private int assetcount;

	
}
