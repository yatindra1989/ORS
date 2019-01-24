package com.raystech.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Course POJO class. It is persistent object.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Entity
@Table(name = "st_course")
public class CourseDTO extends BaseDTO {
	/**
	 * Name of Course
	 */
	@Column(name = "NAME")
	private String courseName;

	/**
	 * Description of Course
	 */
	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * Duration of Course
	 */
	@Column(name = "DURATION")
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
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return courseName;
	}

}
