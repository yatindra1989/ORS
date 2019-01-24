package com.raystech.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.TimeTableDTO;
import com.raystech.proj0.util.Util;

/**
 * Contains TimeTable form elements and their declarative input validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class TimeTableForm extends BaseForm {
	/**
	 * Course ID
	 */
	private long courseId;
	/**
	 * Course Name
	 */
	private String courseName;
	/**
	 * Subject Name
	 */
	@NotEmpty(message = "{error.subject.required}")
	private String subject;
	/**
	 * Date of Exam
	 */
	@NotEmpty(message = "{error.examDate.required}")
	private String examDate;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	@Override
	public BaseDTO getDto(HttpSession session) {
		TimeTableDTO dto = new TimeTableDTO();
		dto.setId(id);
		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		dto.setSubject(subject);
		dto.setExamDate(Util.getDate(examDate));
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
		if (bDto == null) {
			return;
		}
		TimeTableDTO dto = (TimeTableDTO) bDto;
		id = dto.getId();
		courseId = dto.getCourseId();
		courseName = dto.getCourseName();
		subject = dto.getSubject();
		examDate = Util.getDate(dto.getExamDate());
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();

	}
}
