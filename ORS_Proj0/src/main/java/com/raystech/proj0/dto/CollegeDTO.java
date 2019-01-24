package com.raystech.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * College POJO class. It is persistent object.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Entity
@Table(name = "st_college")
public class CollegeDTO extends BaseDTO {
	/**
	 * Name of College
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * Address of College
	 */
	@Column(name = "ADDRESS")
	private String address;

	/**
	 * State of College
	 */
	@Column(name = "STATE")
	private String state;
	/**
	 * City of College
	 */
	@Column(name = "CITY")
	private String city;

	/**
	 * Phone Number of College
	 */
	@Column(name = "PHONE_NO")
	private String phoneNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
