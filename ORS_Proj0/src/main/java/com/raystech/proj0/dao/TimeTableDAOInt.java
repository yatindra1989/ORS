package com.raystech.proj0.dao;

import java.util.Date;
import java.util.List;

import com.raystech.proj0.dto.RoleDTO;
import com.raystech.proj0.dto.TimeTableDTO;

/**
 *
 * Time Table DAO interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface TimeTableDAOInt {

	/**
	 * Adds Time Table
	 * 
	 * @param dto
	 * @return long
	 */
	public long add(TimeTableDTO dto);

	/**
	 * Updates Time Table
	 * 
	 * @param dto
	 */
	public void update(TimeTableDTO dto);

	/**
	 * Deletes Time Table
	 * 
	 * @param dto
	 */
	public void delete(TimeTableDTO dto);

	/**
	 * Finds Time Table by Name
	 * 
	 * @param Name
	 * @return TimeTableDTO
	 */
	public TimeTableDTO findByName(String Name);

	/**
	 * Finds Time Table by Primary Key
	 * 
	 * @param pk
	 * @return TimeTableDTO
	 */
	public TimeTableDTO findByPK(long pk);

	/**
	 * Search Time Table without pagination
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(TimeTableDTO dto);

	/**
	 * Search Time Table with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(TimeTableDTO dto, int pageNo, int pageSize);

	/**
	 * Finds Time Table by Subject and Course ID
	 * 
	 * @param subject
	 * @param courseId
	 * @return TimeTableDTO
	 */
	public TimeTableDTO findBySubjectAndCourseId(String subject, long courseId);

	/**
	 * Finds Time Table by Course ID and Exam Date
	 * 
	 * @param courseId
	 * @param date
	 * @return TimeTableDTO
	 */
	public TimeTableDTO findByCourseIdAndDate(long courseId, Date date);
}
