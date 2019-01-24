package com.raystech.proj0.service;

import java.util.List;

import com.raystech.proj0.dto.RoleDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Role Service interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface RoleServiceInt {

	/**
	 * Adds a Role.
	 *
	 * @param dto
	 * @return long
	 * @throws DuplicateRecordException
	 */
	public long add(RoleDTO dto) throws DuplicateRecordException;

	/**
	 * Updates a Role.
	 *
	 * @param dto
	 * @throws DuplicateRecordException
	 */
	public void update(RoleDTO dto) throws DuplicateRecordException;

	/**
	 * Deletes a Role
	 *
	 * @param id
	 */
	public void delete(long id);

	/**
	 * Finds a Role by name.
	 *
	 * @param roleName
	 * @return RoleDTO
	 */
	public RoleDTO findByName(String roleName);

	/**
	 * Finds a Role by ID
	 *
	 * @param id
	 * @return RoleDTO
	 */
	public RoleDTO findByPK(long id);

	/**
	 * Searches Roles
	 *
	 * @param dto
	 * @return List
	 */
	public List search(RoleDTO dto);

	/**
	 * Searches Roles with pagination.
	 *
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(RoleDTO dto, int pageNo, int pageSize);

}
