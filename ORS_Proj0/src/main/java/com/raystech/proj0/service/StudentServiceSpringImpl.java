package com.raystech.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.raystech.proj0.dao.StudentDAOInt;
import com.raystech.proj0.dto.CollegeDTO;
import com.raystech.proj0.dto.StudentDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Student Service Spring implementation.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service("studentService")
public class StudentServiceSpringImpl implements StudentServiceInt {
	/**
	 * Object of StudentDAOInt
	 */
	@Autowired
	StudentDAOInt dao;
	/**
	 * Object of CollegeServiceInt
	 */
	@Autowired
	CollegeServiceInt cService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(StudentDTO dto) throws DuplicateRecordException {

		CollegeDTO cDto = cService.findByPK(dto.getCollegeId());
		dto.setCollegeName(cDto.getName());

		StudentDTO dtoExist = dao.findByEmail(dto.getEmailId());
		if (dtoExist != null) {
			throw new DuplicateRecordException("Student with this Email id already exist");
		}
		long pk = dao.add(dto);
		return pk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(StudentDTO dto) {
		CollegeDTO cDto = cService.findByPK(dto.getCollegeId());
		dto.setCollegeName(cDto.getName());

		dao.update(dto);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long pk) {
		StudentDTO dtoExist = findByPK(pk);
		dao.delete(dtoExist);
	}

	@Override
	@Transactional(readOnly = true)
	public StudentDTO findByEmail(String emailId) {
		StudentDTO dto = dao.findByEmail(emailId);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public StudentDTO findByPK(long pk) {
		StudentDTO dto = dao.findByPK(pk);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List search(StudentDTO dto) {
		return dao.search(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(StudentDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

}
