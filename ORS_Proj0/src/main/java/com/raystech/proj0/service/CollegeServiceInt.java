package com.raystech.proj0.service;

import java.util.List;
import com.raystech.proj0.dto.CollegeDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * College Service interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface CollegeServiceInt {
	/**
	 * Adds a College
	 * 
	 * @param dto
	 * @return long
	 * @throws DuplicateRecordException
	 */
	public long add(CollegeDTO dto) throws DuplicateRecordException;

	/**
	 * Updates a College
	 * 
	 * @param dto
	 */
	public void update(CollegeDTO dto);

	/**
	 * Deletes a College
	 * 
	 * @param pk
	 */
	public void delete(long pk);

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
	 * Search College without pagination
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(CollegeDTO dto);

	/**
	 * Search College with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return CollegeDTO
	 */
	public List search(CollegeDTO dto, int pageNo, int pageSize);

}
