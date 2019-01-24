package com.raystech.proj0.dao;

import java.util.List;
import com.raystech.proj0.dto.StudentDTO;

/**
 *
 * Student DAO interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface StudentDAOInt {
	/**
	 * Adds a Student
	 * 
	 * @param dto
	 * @return long
	 */
	public long add(StudentDTO dto);

	/**
	 * Updates Student
	 * 
	 * @param dto
	 */
	public void update(StudentDTO dto);

	/**
	 * Deletes Student
	 * 
	 * @param dto
	 */
	public void delete(StudentDTO dto);

	/**
	 * Finds Student by Email ID
	 * 
	 * @param emailId
	 * @return StudenDTO
	 */
	public StudentDTO findByEmail(String emailId);

	/**
	 * Finds Student by Primary Key
	 * 
	 * @param pk
	 * @return StudentDTO
	 */
	public StudentDTO findByPK(long pk);

	/**
	 * Search Student without pagination
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(StudentDTO dto);

	/**
	 * Search Student without pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(StudentDTO dto, int pageNo, int pageSize);

}
