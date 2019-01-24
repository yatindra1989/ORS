package com.raystech.proj0.service;

import java.util.List;

import com.raystech.proj0.dto.FacultyDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Faculty Service interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface FacultyServiceInt {
	/**
	 * Adds Faculty
	 * 
	 * @param dto
	 * @return long
	 * @throws DuplicateRecordException
	 */
	public long add(FacultyDTO dto) throws DuplicateRecordException;

	/**
	 * Update Faculty
	 * 
	 * @param dto
	 */
	public void update(FacultyDTO dto);

	/**
	 * Delete Faculty
	 * 
	 * @param pk
	 */
	public void delete(long pk);

	/**
	 * Find Faculty by Email ID
	 * 
	 * @param email
	 * @return FacultyDTO
	 */
	public FacultyDTO findByEmail(String email);

	/**
	 * Find Faculty by Primary Key
	 * 
	 * @param pk
	 * @return FacultyDTO
	 */
	public FacultyDTO findByPK(long pk);

	/**
	 * Search Faculty without pagination
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(FacultyDTO dto);

	/**
	 * Search Faculty with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(FacultyDTO dto, int pageNo, int pageSize);
}
