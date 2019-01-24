package com.raystech.proj0.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User POJO class. It is persistent object.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "st_user")
public class UserDTO extends BaseDTO implements DropdownList {

	/**
	 * Lock Active constant for User
	 */
	public static final String ACTIVE = "Active";
	/**
	 * Lock Inactive constant for User
	 */
	public static final String INACTIVE = "Inactive";
	/**
	 * First Name of User
	 */
	@Column(name = "FIRST_NAME")
	private String firstName;

	/**
	 * Last Name of User
	 */
	@Column(name = "LAST_NAME")
	private String lastName;

	/**
	 * Login ID of User
	 */
	@Column(name = "LOGIN")
	private String login;

	/**
	 * Password of User
	 */
	@Column(name = "PASSWORD")
	private String password;

	/**
	 * Date of Birth of User
	 */
	@Column(name = "DOB")
	private Date dob;

	/**
	 * Mobile Number of User
	 */
	@Column(name = "MOBILE_NO")
	private String mobileNo;

	/**
	 * Role ID of User
	 */
	@Column(name = "ROLE_ID")
	private long roleId;

	/**
	 * Unsuccessful login
	 */
	@Column(name = "UNSUCCESSFUL_LOGIN")
	private String unsuccessfulLogin;

	/**
	 * Gender of User
	 */
	@Column(name = "GENDER")
	private String gender;

	/**
	 * Last Login IP of the User
	 */
	@Column(name = "LAST_LOGIN")
	private Timestamp lastLogin;

	/**
	 * User Lock
	 */
	@Column(name = "USER_LOCK")
	private String lock;

	/**
	 * Registered IP of User
	 */
	@Column(name = "REGISTERED_IP")
	private String registeredIP;

	/**
	 * Last Login IP of User
	 */
	@Column(name = "LAST_LOGIN_IP")
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
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
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return firstName + " " + lastName;
	}

}
