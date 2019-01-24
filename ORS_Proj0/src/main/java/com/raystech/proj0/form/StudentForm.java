package com.raystech.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.StudentDTO;
import com.raystech.proj0.util.Util;

/**
 * Contains Student form elements and their declarative input validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class StudentForm extends BaseForm {
	/**
	 * College ID
	 */
	private long collegeId;
	/**
	 * Name of College
	 */
	private String collegeName;
	/**
	 * First Name of Student
	 */
	@NotEmpty(message = "{error.firstName.required}")
	private String firstName;
	/**
	 * Last Name of Student
	 */
	@NotEmpty(message = "{error.lastName.required}")
	private String lastName;
	/**
	 * Date of Birth of User
	 */
	@NotEmpty(message = "{error.dob.required}")
	private String dob;
	/**
	 * Email ID of Student
	 */
	@NotEmpty(message = "{error.login.required}")
	private String emailId;
	/**
	 * Mobile Number of Student
	 */
	@NotEmpty(message = "{error.mobileNo.required}")
	private String mobileNo;

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public BaseDTO getDto(HttpSession session) {
		StudentDTO dto = new StudentDTO();
		dto.setId(id);
		dto.setCollegeId(collegeId);
		dto.setCollegeName(collegeName);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setDob(Util.getDate(dob));
		dto.setMobileNo(mobileNo);
		dto.setEmailId(emailId);
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
		StudentDTO dto = (StudentDTO) bDto;
		id = dto.getId();
		collegeId = dto.getCollegeId();
		collegeName = dto.getCollegeName();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		dob = Util.getDate(dto.getDob());
		mobileNo = dto.getMobileNo();
		emailId = dto.getEmailId();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}
}
