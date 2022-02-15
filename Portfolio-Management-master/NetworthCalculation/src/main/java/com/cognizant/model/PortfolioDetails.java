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

@ApiModel(value="Portfolio Details", description = "A Portfolio Model")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfoliodetails")
public class PortfolioDetails {

	@Id
	@Column(name = "portfolioid")
	@ApiModelProperty(notes = "The database generated product ID")
	private int portfolioId;
}
