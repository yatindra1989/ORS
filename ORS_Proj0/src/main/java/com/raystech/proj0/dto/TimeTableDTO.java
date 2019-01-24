package com.raystech.proj0.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Time Table POJO class. It is persistent object.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Entity
@Table(name = "st_time_table")
public class TimeTableDTO extends BaseDTO {
	/**
	 * ID of Course
	 */
	@Column(name = "COURSE_ID")
	private long courseId;
	/**
	 * Name of Course
	 */
	@Column(name = "COURSE_NAME")
	private String courseName;
	/**
	 * Name of Subject
	 */
	@Column(name = "SUBJECT")
	private String subject;
	/**
	 * Date of Exam
	 */
	@Column(name = "EXAM_DATE")
	private Date examDate;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseID) {
		this.courseId = courseID;
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

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return subject;
	}

}
