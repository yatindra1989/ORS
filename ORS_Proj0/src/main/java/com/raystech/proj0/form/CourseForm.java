package com.raystech.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.CourseDTO;

/**
 * Contains Course form elements and their declarative input validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class CourseForm extends BaseForm {
	/**
	 * Name of Course
	 */
	@NotEmpty(message = "{error.courseName.required}")
	private String courseName;
	/**
	 * Description of Course
	 */
	@NotEmpty(message = "{error.courseDescription.required}")
	private String description;
	/**
	 * Duration of Course
	 */
	@NotEmpty(message = "{error.courseDuration.required}")
	private String duration;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public void populate(BaseDTO bDto) {
		if (bDto == null) {
			return;
		}
		CourseDTO dto = (CourseDTO) bDto;
		id = dto.getId();
		courseName = dto.getCourseName();
		description = dto.getDescription();
		duration = dto.getDuration();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}

	@Override
	public BaseDTO getDto(HttpSession session) {
		CourseDTO dto = new CourseDTO();
		dto.setId(id);
		dto.setCourseName(courseName);
		dto.setDescription(description);
		dto.setDuration(duration);
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
