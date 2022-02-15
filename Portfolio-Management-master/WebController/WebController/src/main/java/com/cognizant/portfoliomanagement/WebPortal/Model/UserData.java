package com.cognizant.portfoliomanagement.WebPortal.Model;

import org.springframework.stereotype.Component;

/** Model class for the business details */
@Component
public class UserData {

	/**
	 * Id for user
	 */

	private String userid;
	/**
	 * Password for user
	 */
	private String upassword;
	/**
	 * Name for user
	 */
	private String uname;
	/**
	 * Generated authentication token for the user
	 */
	private String authToken;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public UserData(String userid, String upassword, String uname, String authToken) {
		super();
		this.uname = uname;
		this.upassword = upassword;
		this.userid = userid;
		this.authToken = authToken;
	}

	public UserData() {

	}

	@Override
	public String toString() {
		return "UserData [userid=" + userid + ", upassword=" + upassword + ", uname=" + uname + ", authToken="
				+ authToken + "]";
	}
	
}
