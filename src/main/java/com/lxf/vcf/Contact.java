package com.lxf.vcf;

public class Contact {
	private String trueName;	//姓名
	private String mobile;		//移动电话
	private String workMobile;	//公司电话
	private String telePhone;	//家庭电话
	private String email;		//邮箱
	private String workURL;		//公司网址
	private String homeURL;		//个人网址
	private String workAddress;	//公司地址
	private String homeAddress;	//家庭地址
	

	
	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getWorkURL() {
		return workURL;
	}

	public void setWorkURL(String workURL) {
		this.workURL = workURL;
	}

	public String getHomeURL() {
		return homeURL;
	}

	public void setHomeURL(String homeURL) {
		this.homeURL = homeURL;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWorkMobile() {
		return workMobile;
	}

	public void setWorkMobile(String workMobile) {
		this.workMobile = workMobile;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
