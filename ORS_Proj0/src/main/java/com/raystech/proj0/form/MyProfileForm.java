package com.raystech.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.util.Util;

/**
 * Contains MyProfile form elements and their declarative input validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class MyProfileForm extends BaseForm {
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
	private String login;
	/**
	 * Password of User
	 */
	private String password;
	/**
	 * Date of birth of User
	 */
	@NotEmpty(message = "{error.dob.required}")
	private String dob;
	/**
	 * Mobile number of User
	 */
	@NotEmpty(message = "{error.mobileNo.required}")
	private String mobileNo;
	/**
	 * Role ID of User
	 */
	private long roleId;
	/**
	 * Gender of User
	 */
	@NotEmpty(message = "{error.gender.required}")
	private String gender;
	/**
	 * Unsuccessfull login
	 */
	private String unsuccessfulLogin;
	/**
	 * Last login IP of User
	 */
	private long lastLogin;
	/**
	 * User lock
	 */
	private String lock;
	/**
	 * Registered IP of User
	 */
	private String registeredIP;
	/**
	 * Last Login IP of User
	 */
	private String lastLoginIP;

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

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUnsuccessfulLogin() {
		return unsuccessfulLogin;
	}

	public void setUnsuccessfulLogin(String unsuccessfulLogin) {
		this.unsuccessfulLogin = unsuccessfulLogin;
	}

	public long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getRegisteredIP() {
		return registeredIP;
	}

	public void setRegisteredIP(String registeredIP) {
		this.registeredIP = registeredIP;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	@Override
	public BaseDTO getDto(HttpSession session) {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setLogin(login);
		dto.setGender(gender);
		dto.setRoleId(roleId);
		dto.setPassword(password);
		dto.setMobileNo(mobileNo);
		dto.setDob(Util.getDate(dob));
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
		gender = dto.getGender();
		password = dto.getPassword();
		roleId = dto.getRoleId();
		mobileNo = dto.getMobileNo();
		dob = Util.getDate(dto.getDob());
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}
}
