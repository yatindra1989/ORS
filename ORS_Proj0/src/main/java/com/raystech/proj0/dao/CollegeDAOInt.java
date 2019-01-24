package com.raystech.proj0.dao;

import java.util.List;
import com.raystech.proj0.dto.CollegeDTO;

/**
 *
 * College DAO interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface CollegeDAOInt {
	/**
	 * Adds College
	 * 
	 * @param dto
	 * @return long
	 */
	public long add(CollegeDTO dto);

	/**
	 * Updates College
	 * 
	 * @param dto
	 */
	public void update(CollegeDTO dto);

	/**
	 * Deletes College
	 * 
	 * @param dto
	 */
	public void delete(CollegeDTO dto);

	/**
	 * Finds College by Name
	 * 
	 * @param name
	 * @return CollegeDTO
	 */
	public CollegeDTO findByName(String name);

	/**
	 * Finds College by Primary Key
	 * 
	 * @param pk
	 * @return CollegeDTO
	 */
	public CollegeDTO findByPK(long pk);

	/**
	 * Searches college without pagination
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(CollegeDTO dto);

	/**
	 * Searches college without pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(CollegeDTO dto, int pageNo, int pageSize);

}
