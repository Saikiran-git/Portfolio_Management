package com.cognizant.portfoliomanagement.WebPortal.Model;


import org.springframework.stereotype.Component;

@Component
public class Asset {
	
	
	private int tid;
	
	private int portfolioid;
	
	private String assetid;
	
	private String type;
	
	private int units;
	
	

	public int getTid() {
		return tid;
	}



	public void setTid(int tid) {
		this.tid = tid;
	}



	public int getPortfolioid() {
		return portfolioid;
	}



	public void setPortfolioid(int portfolioid) {
		this.portfolioid = portfolioid;
	}



	public String getAssetid() {
		return assetid;
	}



	public void setAssetid(String assetid) {
		this.assetid = assetid;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public int getUnits() {
		return units;
	}



	public void setUnits(int units) {
		this.units = units;
	}
	

	public Asset(int tid, int portfolioid, String assetid, String type, int units) {
		super();
		this.tid = tid;
		this.portfolioid = portfolioid;
		this.assetid = assetid;
		this.type = type;
		this.units = units;
	}


	public Asset() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "Asset [tid=" + tid + ", portfolioid=" + portfolioid + ", assetid=" + assetid + ", type=" + type
				+ ", units=" + units + "]";
	}
	
	

}
