package com.raystech.proj0.dao;

import java.util.List;

import com.raystech.proj0.dto.MarksheetDTO;

/**
 *
 * Marksheet DAO interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface MarksheetDAOInt {
	/**
	 * Adds Marksheet
	 * 
	 * @param dto
	 * @return long
	 */
	public long add(MarksheetDTO dto);

	/**
	 * Updates Marksheet
	 * 
	 * @param dto
	 */
	public void update(MarksheetDTO dto);

	/**
	 * Deletes Marksheet
	 * 
	 * @param dto
	 */
	public void delete(MarksheetDTO dto);

	/**
	 * Finds Marksheet by Roll Number
	 * 
	 * @param rollNo
	 * @return MarksheetDTO
	 */
	public MarksheetDTO findByRollNo(String rollNo);

	/**
	 * Finds Marksheet by Primary Key
	 * 
	 * @param pk
	 * @return MarksheetDTO
	 */
	public MarksheetDTO findByPK(long pk);

	/**
	 * Searches Marksheet without pagingation
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(MarksheetDTO dto);

	/**
	 * Searches Marksheet with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(MarksheetDTO dto, int pageNo, int pageSize);

	/**
	 * Get Merit list of Students
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List getMeritList(int pageNo, int pageSize);

}
