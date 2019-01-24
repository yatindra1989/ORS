package com.raystech.proj0.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Faculty POJO class. It is persistent object.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Entity
@Table(name = "st_faculty")
public class FacultyDTO extends BaseDTO {
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
	/**
	 * First Name of Faculty
	 */
	@Column(name = "FIRST_NAME")
	private String firstName;
	/**
	 * Last Name of Faculty
	 */
	@Column(name = "LAST_NAME")
	private String lastName;

	/**
	 * Date of Birth of Faculty
	 */
	@Column(name = "DOB")
	private Date dob;

	/**
	 * Email ID of Faculty
	 */
	@Column(name = "EMAIL_ID")
	private String emailId;

	/**
	 * Mobile Number of Faculty
	 */
	@Column(name = "MOBILE_NO")
	private String mobileNo;

	/**
	 * Subject
	 */
	@Column(name = "SUBJECT")
	private String subject;

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

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

}
