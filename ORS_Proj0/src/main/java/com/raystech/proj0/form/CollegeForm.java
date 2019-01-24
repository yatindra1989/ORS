package com.raystech.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.CollegeDTO;

/**
 * Contains College form elements and their declarative input validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class CollegeForm extends BaseForm {
	/**
	 * Name of College
	 */
	@NotEmpty(message = "{error.college.required}")
	private String name;
	/**
	 * Address of College
	 */
	@NotEmpty(message = "{error.address.required}")
	private String address;
	/**
	 * State of College
	 */
	@NotEmpty(message = "{error.state.required}")
	private String state;
	/**
	 * City of College
	 */
	@NotEmpty(message = "{error.city.required}")
	private String city;
	/**
	 * Phone number of College
	 */
	@NotEmpty(message = "{error.phoneNo.required}")
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
	public BaseDTO getDto(HttpSession session) {
		CollegeDTO dto = new CollegeDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setAddress(address);
		dto.setState(state);
		dto.setCity(city);
		dto.setPhoneNo(phoneNo);
		getGeneric(session);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		if (createdDatetime > 0) {
			System.out.println("11");
			dto.setCreatedDatetime(new Timestamp(createdDatetime));
		} else {
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		}
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		return dto;

	}

	@Override
	public void populate(BaseDTO bDto) {
		CollegeDTO dto = (CollegeDTO) bDto;
		id = dto.getId();
		name = dto.getName();
		address = dto.getAddress();
		state = dto.getState();
		city = dto.getCity();
		phoneNo = dto.getPhoneNo();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
		super.populate(bDto);
	}

}
