package com.raystech.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.raystech.proj0.dao.RoleDAOInt;
import com.raystech.proj0.dto.RoleDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Role Service Spring implementation
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service("roleService")
public class RoleServiceSpringImpl implements RoleServiceInt {
	/**
	 * Object of RoleDAOInt
	 */
	@Autowired
	private RoleDAOInt dao;

	private static Logger log = Logger.getLogger(RoleServiceSpringImpl.class);

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(RoleDTO dto) throws DuplicateRecordException {
		log.debug("Role Service Add Started");
		RoleDTO dtoExist = dao.findByName(dto.getRoleName());
		if (dtoExist != null) {
			throw new DuplicateRecordException("Role Name already exists");
		}
		long pk = dao.add(dto);
		log.debug("Role Service Add Ended");
		return pk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void update(RoleDTO dto) throws DuplicateRecordException {
		log.debug("Role Service update Started");
		// RoleDTO dtoExist = dao.findByName(dto.getRoleName());
		dao.update(dto);
		log.debug("Role Service update End");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(long id) {
		log.debug("Role Service delete Start");
		RoleDTO dtoExist = findByPK(id);
		dao.delete(dtoExist);
		log.debug("Role Service delete End");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public RoleDTO findByName(String roleName) {
		log.debug("Role Service findByName Started");
		RoleDTO dto = dao.findByName(roleName);
		log.debug("Role Service findByName Ended");
		return dto;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public RoleDTO findByPK(long id) {
		log.debug("Role Service findById Started");
		RoleDTO dto = dao.findByPK(id);
		log.debug("Role Service findById Ended");
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List search(RoleDTO dto) {
		return dao.search(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(RoleDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

}
