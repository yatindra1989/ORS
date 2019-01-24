package com.raystech.proj0.service;

import java.util.List;
import com.raystech.proj0.dto.StudentDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Student Service interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface StudentServiceInt {
	/**
	 * Adds a Student
	 * 
	 * @param dto
	 * @return long
	 * @throws DuplicateRecordException
	 */
	public long add(StudentDTO dto) throws DuplicateRecordException;

	/**
	 * Updates Student
	 * 
	 * @param dto
	 */
	public void update(StudentDTO dto);

	/**
	 * Deletes Student
	 * 
	 * @param pk
	 */
	public void delete(long pk);

	/**
	 * Finds Student by EmailID
	 * 
	 * @param emailId
	 * @return StudentDTO
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
	 * Search Student with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(StudentDTO dto, int pageNo, int pageSize);

}
