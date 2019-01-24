package com.raystech.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.raystech.proj0.dao.CollegeDAOInt;
import com.raystech.proj0.dto.CollegeDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * College Service Spring implementation.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service(value = "collegeService")
public class CollegeServiceSpringImpl implements CollegeServiceInt {

	/**
	 * Object of CollegeDAOInt
	 */
	@Autowired
	private CollegeDAOInt dao = null;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(CollegeDTO dto) throws DuplicateRecordException {
		CollegeDTO dtoExist = dao.findByName(dto.getName());
		if (dtoExist != null) {
			throw new DuplicateRecordException("College Name already exists");
		}
		long pk = dao.add(dto);
		return pk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(CollegeDTO dto) {
		dao.update(dto);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long pk) {
		CollegeDTO dtoExist = dao.findByPK(pk);
		dao.delete(dtoExist);

	}

	@Override
	@Transactional(readOnly = true)
	public CollegeDTO findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public CollegeDTO findByPK(long pk) {
		return dao.findByPK(pk);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(CollegeDTO dto) {
		return dao.search(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(CollegeDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

}
