package com.raystech.proj0.dao;

import java.util.List;

import com.raystech.proj0.dto.CourseDTO;

/**
 *
 * Course DAO interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface CourseDAOInt {
	/**
	 * Adds Course
	 * 
	 * @param dto
	 * @return long
	 */
	public long add(CourseDTO dto);

	/**
	 * Updates Course
	 * 
	 * @param dto
	 */
	public void update(CourseDTO dto);

	/**
	 * Deletes Course
	 * 
	 * @param dto
	 */
	public void delete(CourseDTO dto);

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
	 * @return CourseDTO
	 */
	public CourseDTO findByPK(long pk);

	/**
	 * Searches Course without pagination
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(CourseDTO dto);

	/**
	 * Searches Course with paginatino
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(CourseDTO dto, int pageNo, int pageSize);

}
