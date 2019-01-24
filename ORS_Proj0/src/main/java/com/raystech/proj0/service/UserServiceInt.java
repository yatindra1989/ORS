package com.raystech.proj0.service;

import java.util.List;

import javax.mail.MessagingException;

import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * User Service interface.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface UserServiceInt {

	/**
	 * Adds a User
	 * 
	 * @param dto
	 * @return long
	 * @throws DuplicateRecordException
	 */
	public long add(UserDTO dto) throws DuplicateRecordException;

	/**
	 * Updates a User
	 * 
	 * @param dto
	 */
	public void update(UserDTO dto);

	/**
	 * Registers a User
	 * 
	 * @param dto
	 * @return long
	 * @throws DuplicateRecordException
	 * @throws MessagingException
	 */
	public long registerUser(UserDTO dto) throws DuplicateRecordException, MessagingException;

	/**
	 * Deletes a User
	 * 
	 * @param id
	 */
	public void delete(long id);

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
	 * Changes password of User
	 * 
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return boolean
	 */
	public boolean changePassword(Long id, String oldPassword, String newPassword);

	/**
	 * Authenticates a User when login
	 * 
	 * @param dto
	 * @return UserDTO
	 */
	public UserDTO authenticate(UserDTO dto);

	/**
	 * Sets lock of the user
	 * 
	 * @param login
	 * @return boolean
	 */
	public boolean lock(String login);

	/**
	 * Reset User's Password
	 * 
	 * @param login
	 * @param newPassword
	 * @return boolean
	 * @throws DuplicateRecordException
	 * @throws MessagingException
	 */
	public boolean resetPassword(String login, String newPassword) throws DuplicateRecordException, MessagingException;

	/**
	 * Send mail to User's ID while requesting password
	 * 
	 * @param login
	 * @return String
	 */
	public String forgetPassword(String login);

	/**
	 * Updates Access of User
	 * 
	 * @param dto
	 * @return UserDTO
	 */
	public UserDTO updateAccess(UserDTO dto);
}
