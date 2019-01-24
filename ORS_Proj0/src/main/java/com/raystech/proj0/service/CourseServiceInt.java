package com.raystech.proj0.service;

import java.util.List;

import com.raystech.proj0.dto.CourseDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Course Service interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface CourseServiceInt {
	/**
	 * Adds Course
	 * 
	 * @param dto
	 * @return long
	 * @throws DuplicateRecordException
	 */
	public long add(CourseDTO dto) throws DuplicateRecordException;

	/**
	 * Updates Course
	 * 
	 * @param dto
	 */
	public void update(CourseDTO dto);

	/**
	 * Delete Course
	 * 
	 * @param id
	 */
	public void delete(long id);

	/**
	 * Finds Course by Name
	 * 
	 * @param name
	 * @return CourseDTO
	 */
	public CourseDTO findByName(String name);

	/**
	 * Finds Course by Primary Key
	 * 
	 * @param pk
	 * @return CourseDto
	 */
	public CourseDTO findByPK(long pk);

	/**
	 * Search Course without pagination
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(CourseDTO dto);

	/**
	 * Search Course with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(CourseDTO dto, int pageNo, int pageSize);

}
