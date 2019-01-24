package com.raystech.proj0.service;

import java.util.List;

import com.raystech.proj0.dto.MarksheetDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Marksheet Service interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface MarksheetServiceInt {

	/**
	 * Adds Marksheet
	 * 
	 * @param dto
	 * @return long
	 * @throws DuplicateRecordException
	 */
	public long add(MarksheetDTO dto) throws DuplicateRecordException;

	/**
	 * Updates Marksheet
	 * 
	 * @param dto
	 */
	public void update(MarksheetDTO dto);

	/**
	 * Delets Marksheet
	 * 
	 * @param pk
	 */
	public void delete(long pk);

	/**
	 * Find Marksheet by Roll Number
	 * 
	 * @param rollNo
	 * @return MarksheetDTO
	 */
	public MarksheetDTO findByRollNo(String rollNo);

	/**
	 * Find Marksheet by Primary Key
	 * 
	 * @param pk
	 * @return MarksheetDTO
	 */
	public MarksheetDTO findByPK(long pk);

	/**
	 * Search Marksheet without pagination
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(MarksheetDTO dto);

	/**
	 * Search Marksheet with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return MarksheetDTO
	 */
	public List search(MarksheetDTO dto, int pageNo, int pageSize);

	public List getMeritList(int pageNo, int pageSize);

	public void sendMarksheetOnEmail(String rollNo, String email);
}
