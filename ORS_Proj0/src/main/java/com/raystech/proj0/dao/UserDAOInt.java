package com.raystech.proj0.dao;

import java.util.List;

import com.raystech.proj0.dto.UserDTO;

/**
 *
 * User DAO interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface UserDAOInt {

	/**
	 * Adds a User
	 * 
	 * @param dto
	 * @return long
	 */
	public long add(UserDTO dto);

	/**
	 * Updates a User
	 * 
	 * @param dto
	 */
	public void update(UserDTO dto);

	/**
	 * Deletes a User
	 * 
	 * @param dto
	 */
	public void delete(UserDTO dto);

	/**
	 * Finds User by Login ID
	 * 
	 * @param login
	 * @return UserDTO
	 */
	public UserDTO findByLogin(String login);

	/**
	 * Finds User by Primary Key
	 * 
	 * @param pk
	 * @return UserDTO
	 */
	public UserDTO findByPK(long pk);

	/**
	 * Search User without pagination
	 * 
	 * @param dto
	 * @return List
	 */
	public List search(UserDTO dto);

	/**
	 * Search User with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 */
	public List search(UserDTO dto, int pageNo, int pageSize);

	/**
	 * Authenticate User while login
	 * 
	 * @param dto
	 * @return UserDTO
	 */
	public UserDTO authenticate(UserDTO dto);

}
