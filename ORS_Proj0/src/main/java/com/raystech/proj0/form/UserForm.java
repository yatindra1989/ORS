package com.raystech.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.util.Util;

/**
 * Contains User form elements and their declarative input validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class UserForm extends BaseForm {
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
	 * Gender of user
	 */
	@NotEmpty(message = "{error.gender.required}")
	private String gender;
	/**
	 * Unsuccessfull Login variable of user
	 */
	private String unsuccessfulLogin;
	/**
	 * Last Login IP of User
	 */
	private long lastLogin;
	/**
	 * User Lock
	 */
	private String lock;
	/**
	 * Registered IP of user
	 */
	private String registeredIP;
	/**
	 * Last LoginIP of User
	 */
	private String lastLoginIP;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

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

	public String getUnsuccessfulLogin() {
		return unsuccessfulLogin;
	}

	public void setUnsuccessfulLogin(String unsuccessfulLogin) {
		this.unsuccessfulLogin = unsuccessfulLogin;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
		dto.setPassword(password);
		dto.setDob(Util.getDate(dob));
		dto.setRoleId(roleId);
		dto.setMobileNo(mobileNo);
		dto.setLastLogin(new Timestamp(lastLogin));
		dto.setGender(gender);
		dto.setRegisteredIP(registeredIP);
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
		confirmPassword = dto.getPassword();
		dob = Util.getDate(dto.getDob());
		mobileNo = dto.getMobileNo();
		roleId = dto.getRoleId();
		if (dto.getLastLogin() != null) {
			lastLogin = dto.getLastLogin().getTime();
		}
		gender = dto.getGender();
		registeredIP = dto.getRegisteredIP();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		if (dto.getCreatedDatetime() != null) {
			createdDatetime = dto.getCreatedDatetime().getTime();
		}
		if (dto.getModifiedDatetime() != null) {
			modifiedDatetime = dto.getModifiedDatetime().getTime();
		}

	}

}
