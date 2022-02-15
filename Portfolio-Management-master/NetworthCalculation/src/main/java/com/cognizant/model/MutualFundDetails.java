package com.cognizant.model;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MutualFundDetails {

	private String mutualFundId;
	private String mutualFundName;
	private double mutualFundValue;
}
