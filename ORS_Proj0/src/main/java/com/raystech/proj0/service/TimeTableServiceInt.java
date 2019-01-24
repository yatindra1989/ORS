package com.raystech.proj0.service;

import java.util.Date;
import java.util.List;

import com.raystech.proj0.dto.TimeTableDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Time Table Service interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface TimeTableServiceInt {
	/**
	 * Adds a Time Table
	 * 
	 * @param dto
	 * @return long
	 * @throws DuplicateRecordException
	 */
	public long add(TimeTableDTO dto) throws DuplicateRecordException;

	/**
	 * Updates Time Table
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 */
	public void update(TimeTableDTO dto) throws DuplicateRecordException;

	/**
	 * Deletes a Time Table
	 * 
	 * @param pk
	 */
	public void delete(long pk);

	/**
	 * Find Time Table by Name
	 * 
	 * @param Name
	 * @return TimeTableDTO
	 */
	public TimeTableDTO findByName(String Name);

	/**
	 * Finds TimeTable by Primary Key
	 * 
	 * @param pk
	 * @return TimeTableDTO
	 */
	public TimeTableDTO findByPK(long pk);

	/**
	 * Search TimeTable without pagination
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(TimeTableDTO dto);

	/**
	 * Search TimeTable with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(TimeTableDTO dto, int pageNo, int pageSize);

	/**
	 * Find TimeTable by Subject and courseId
	 * 
	 * @param subject
	 * @param courseId
	 * @return TimeTableDTO
	 */
	TimeTableDTO findBySubjectAndCourseId(String subject, long courseId);

	/**
	 * Find TimeTable by CourseId and Exam Date
	 * 
	 * @param courseId
	 * @param date
	 * @return TimeTableDTO
	 */
	TimeTableDTO findByCourseIdAndDate(long courseId, Date date);

}
