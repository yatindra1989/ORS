package com.raystech.proj0.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Student POJO class. It is persistent object.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "st_student")
public class StudentDTO extends BaseDTO {

	/**
	 * First Name of Student
	 */
	@Column(name = "FIRST_NAME")
	private String firstName;
	/**
	 * Last Name of Student
	 */
	@Column(name = "LAST_NAME")
	private String lastName;
	/**
	 * Date of Birth of Student
	 */
	@Column(name = "DATE_OF_BIRTH")
	private Date dob;
	/**
	 * Mobile no of Student
	 */
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	/**
	 * Email of Student
	 */
	@Column(name = "EMAIL")
	private String emailId;
	/**
	 * ID of College
	 */
	@Column(name = "COLLEGE_ID")
	private long collegeId;
	/**
	 * Name of College
	 */
	@Column(name = "COLLEGE_NAME")
	private String collegeName;

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

}
