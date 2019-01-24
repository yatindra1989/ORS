package com.raystech.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.util.Util;

/**
 * Contains UserRegistration form elements and their declarative input
 * validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class UserRegistrationForm extends BaseForm {

	/**
	 * First Name of User
	 */
	@NotEmpty(message = "{error.firstName.required}")
	private String firstName;

	/**
	 * Last Name of User
	 */
	@NotEmpty(message = "{error.lastName.required}")
	private String lastName;
	/**
	 * Login ID of User
	 */
	@NotEmpty(message = "{error.login.required}")
	private String login;
	/**
	 * Password of User
	 */
	@NotEmpty(message = "{error.password.required}")
	private String password;
	/**
	 * Confirm Password
	 */
	@NotEmpty(message = "{error.confirmPassword.required}")
	private String confirmPassword;
	/**
	 * Date of birth of user
	 */
	@NotEmpty(message = "{error.dob.required}")
	private String dob;
	/**
	 * Mobile number of user
	 */
	@NotEmpty(message = "{error.mobileNo.required}")
	private String mobileNo;
	/**
	 * Gender of user
	 */
	@NotEmpty(message = "{error.gender.required}")
	private String gender;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public BaseDTO getDto(HttpSession session) {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setLogin(login);
		dto.setPassword(password);
		dto.setDob(Util.getDate(dob));
		dto.setMobileNo(mobileNo);
		dto.setGender(gender);
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
		UserDTO dto = (UserDTO) bDto;
		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		login = dto.getLogin();
		password = dto.getPassword();
		dob = Util.getDate(dto.getDob());
		mobileNo = dto.getMobileNo();
		gender = dto.getGender();
	}

}