package com.raystech.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.FacultyDTO;
import com.raystech.proj0.util.Util;

/**
 * Contains Faculty form elements and their declarative input validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class FacultyForm extends BaseForm {
	/**
	 * College ID
	 */
	private long collegeId;
	/**
	 * Name of College
	 */
	private String collegeName;
	/**
	 * First Name of Faculty
	 */
	@NotEmpty(message = "{error.firstName.required}")
	private String firstName;
	/**
	 * Last Name of Faculty
	 */
	@NotEmpty(message = "{error.lastName.required}")
	private String lastName;
	/**
	 * Date of birth of Faculty
	 */
	@NotEmpty(message = "{error.dob.required}")
	private String dob;
	/**
	 * Email ID of Faculty
	 */
	@NotEmpty(message = "{error.login.required}")
	private String emailId;
	/**
	 * Mobile number of Faculty
	 */
	@NotEmpty(message = "{error.mobileNo.required}")
	private String mobileNo;
	/**
	 * Subject to be taught by Faculty
	 */
	@NotEmpty(message = "{error.subject.required}")
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	@Override
	public BaseDTO getDto(HttpSession session) {
		FacultyDTO dto = new FacultyDTO();
		dto.setId(id);
		dto.setCollegeId(collegeId);
		dto.setCollegeName(collegeName);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setDob(Util.getDate(dob));
		dto.setEmailId(emailId);
		dto.setMobileNo(mobileNo);
		dto.setSubject(subject);
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
		FacultyDTO dto = (FacultyDTO) bDto;
		id = dto.getId();
		collegeId = dto.getCollegeId();
		collegeName = dto.getCollegeName();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		dob = Util.getDate(dto.getDob());
		emailId = dto.getEmailId();
		mobileNo = dto.getMobileNo();
		subject = dto.getSubject();
		createdBy = dto.getCreatedBy();
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedBy = dto.getModifiedBy();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
		super.populate(bDto);
	}
}
