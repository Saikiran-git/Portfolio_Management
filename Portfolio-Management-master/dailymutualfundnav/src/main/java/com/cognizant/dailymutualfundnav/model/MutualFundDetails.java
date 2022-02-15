package com.cognizant.dailymutualfundnav.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value="MutualFund Details", description = "A MutualFund Model")
@Entity
@Table(name="mutualfunddetails")
public class MutualFundDetails {

	@Id
	@Column(name="mutualfund_id")
	@ApiModelProperty(notes = "The database generated product ID")
	private String mutualFundId;
	
	@Column(name="mutualfund_name")
	@ApiModelProperty(notes = "The name of a stock", required = true)
	private String mutualFundName;
	
	@Column(name="mutualfund_value")
	@ApiModelProperty(notes = "The value of a stock", required = true)
	private double mutualFundValue;
	
	public MutualFundDetails(String mutualFundId, String mutualFundName, double mutualFundValue) {
		super();
		this.mutualFundId = mutualFundId;
		this.mutualFundName = mutualFundName;
		this.mutualFundValue = mutualFundValue;
	}
	
	public MutualFundDetails() {
		super();
	}
	
	public String getMutualFundId() {
		return mutualFundId;
	}
	public void setMutualFundId(String mutualFundId) {
		this.mutualFundId = mutualFundId;
	}
	public String getMutualFundName() {
		return mutualFundName;
	}
	public void setMutualFundName(String mutualFundName) {
		this.mutualFundName = mutualFundName;
	}
	public double getMutualFundValue() {
		return mutualFundValue;
	}
	public void setMutualFundValue(double mutualFundValue) {
		mutualFundValue = mutualFundValue;
	}
	
	@Override
	public String toString() {
		return "MutualFundDetails [mutualFundId=" + mutualFundId + ", mutualFundName=" + mutualFundName
				+ ", MutualFundValue=" + mutualFundValue + "]";
	}
	
	
}
