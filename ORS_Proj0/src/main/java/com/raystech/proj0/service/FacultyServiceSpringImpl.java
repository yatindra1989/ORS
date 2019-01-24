package com.raystech.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.raystech.proj0.dao.FacultyDAOInt;
import com.raystech.proj0.dto.CollegeDTO;
import com.raystech.proj0.dto.FacultyDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Faculty Service Spring implementation.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service("facultyService")
public class FacultyServiceSpringImpl implements FacultyServiceInt {

	/**
	 * Object of FacultyDAOInt
	 */
	@Autowired
	FacultyDAOInt dao;
	/**
	 * Object of CollegeServiceInt
	 */
	@Autowired
	CollegeServiceInt cService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(FacultyDTO dto) throws DuplicateRecordException {

		CollegeDTO cdto = cService.findByPK(dto.getCollegeId());
		dto.setCollegeName(cdto.getName());

		FacultyDTO dtoExist = dao.findByEmail(dto.getEmailId());
		if (dtoExist != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		long pk = dao.add(dto);
		return pk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(FacultyDTO dto) {

		CollegeDTO cdto = cService.findByPK(dto.getCollegeId());
		dto.setCollegeName(cdto.getName());

		dao.update(dto);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long pk) {
		FacultyDTO dtoExist = findByPK(pk);
		dao.delete(dtoExist);
	}

	@Override
	@Transactional(readOnly = true)
	public FacultyDTO findByEmail(String email) {
		FacultyDTO dto = dao.findByEmail(email);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public FacultyDTO findByPK(long pk) {
		FacultyDTO dto = dao.findByPK(pk);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List search(FacultyDTO dto) {
		return dao.search(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(FacultyDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

}
