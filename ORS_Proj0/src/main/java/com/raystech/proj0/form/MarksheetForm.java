package com.raystech.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.mysql.jdbc.Util;
import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.MarksheetDTO;
import com.raystech.proj0.util.DataValidator;

/**
 * Contains Marksheet form elements and their declarative input validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class MarksheetForm extends BaseForm {
	/**
	 * Roll Number of Student
	 */
	@NotEmpty(message = "{error.rollNo.required}")
	private String rollNo;
	/**
	 * Student ID
	 */
	private long studentId;
	/**
	 * Name of Student
	 */
	private String name;
	/**
	 * Marks of Physics
	 */
	@NotEmpty(message = "{error.physics.required}")
	private String physics;
	/**
	 * Marks of Chemistry
	 */
	@NotEmpty(message = "{error.chemistry.required}")
	private String chemistry;
	/**
	 * Marks of Math
	 */
	@NotEmpty(message = "{error.maths.required}")
	private String maths;
	/**
	 * Total Marks
	 */
	private int total;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhysics() {
		return physics;
	}

	public void setPhysics(String physics) {
		this.physics = physics;
	}

	public String getChemistry() {
		return chemistry;
	}

	public void setChemistry(String chemistry) {
		this.chemistry = chemistry;
	}

	public String getMaths() {
		return maths;
	}

	public void setMaths(String maths) {
		this.maths = maths;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public void populate(BaseDTO bDto) {
		MarksheetDTO dto = (MarksheetDTO) bDto;
		id = dto.getId();
		rollNo = dto.getRollNo();
		studentId = dto.getStudentId();
		name = dto.getName();
		physics = String.valueOf(dto.getPhysics());
		chemistry = String.valueOf(dto.getChemistry());
		maths = String.valueOf(dto.getMaths());
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}

	@Override
	public BaseDTO getDto(HttpSession session) {
		MarksheetDTO dto = new MarksheetDTO();
		dto.setId(id);
		dto.setRollNo(rollNo);
		dto.setStudentId(studentId);
		dto.setName(name);
		if (physics != "" && physics != null && DataValidator.isInteger(physics)) {
			dto.setPhysics(Integer.parseInt(physics));
		}
		if (chemistry != "" && chemistry != null && DataValidator.isInteger(chemistry)) {
			dto.setChemistry(Integer.parseInt(chemistry));
		}
		if (maths != "" && maths != null && DataValidator.isInteger(maths)) {
			dto.setMaths(Integer.parseInt(maths));
		}
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

}
