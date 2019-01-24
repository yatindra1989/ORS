package com.raystech.proj0.dao;

import java.util.List;

import com.raystech.proj0.dto.RoleDTO;

/**
 *
 * Role DAO interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface RoleDAOInt {

	/**
	 * Adds a Role
	 * 
	 * @param dto
	 * @return long
	 */
	public long add(RoleDTO dto);

	/**
	 * Updates a Role
	 * 
	 * @param dto
	 */
	public void update(RoleDTO dto);

	/**
	 * Deletes a Role
	 * 
	 * @param dto
	 */
	public void delete(RoleDTO dto);

	/**
	 * Finds Role by Name
	 * 
	 * @param roleName
	 * @return RoleDTO
	 */
	public RoleDTO findByName(String roleName);

	/**
	 * Finds Role by Primary Key
	 * 
	 * @param pk
	 * @return RoleDTO
	 */
	public RoleDTO findByPK(long pk);

	/**
	 * Searches Role
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(RoleDTO dto);

	/**
	 * Searches Role with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(RoleDTO dto, int pageNo, int pageSize);
}
