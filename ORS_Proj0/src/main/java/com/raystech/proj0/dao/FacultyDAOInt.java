package com.raystech.proj0.dao;

import java.util.List;

import com.raystech.proj0.dto.FacultyDTO;

/**
 *
 * FAculty DAO interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface FacultyDAOInt {

	/**
	 * Adds Faculty
	 * 
	 * @param dto
	 * @return longs
	 */
	public long add(FacultyDTO dto);

	/**
	 * Updates Faculty
	 * 
	 * @param dto
	 */
	public void update(FacultyDTO dto);

	/**
	 * Deletes Faculty
	 * 
	 * @param dto
	 */
	public void delete(FacultyDTO dto);

	/**
	 * Finds Faculty by Email ID
	 * 
	 * @param email
	 * @return FacultyDTO
	 */
	public FacultyDTO findByEmail(String email);

	/**
	 * Finds Faculty by Primary Key
	 * 
	 * @param pk
	 * @return FacultyDTO
	 */
	public FacultyDTO findByPK(long pk);

	/**
	 * Searches Faculty without pagination
	 * 
	 * @param dto
	 * @return FacultyDTO
	 */
	public List search(FacultyDTO dto);

	/**
	 * Searches Faculty with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(FacultyDTO dto, int pageNo, int pageSize);

}
