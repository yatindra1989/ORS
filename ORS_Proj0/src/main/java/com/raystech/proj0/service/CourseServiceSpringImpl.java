package com.raystech.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.raystech.proj0.dao.CourseDAOInt;
import com.raystech.proj0.dto.CourseDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Faculty Service Spring implementation.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service("courseService")
public class CourseServiceSpringImpl implements CourseServiceInt {

	/**
	 * Object of CourseDAOInt
	 */
	@Autowired
	private CourseDAOInt dao = null;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(CourseDTO dto) throws DuplicateRecordException {
		CourseDTO dtoExist = dao.findByName(dto.getCourseName());
		if (dtoExist != null) {
			throw new DuplicateRecordException("Course already exists");
		}
		long pk = dao.add(dto);
		return pk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void update(CourseDTO dto) {
		dao.update(dto);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(long id) {
		CourseDTO dtoExist = dao.findByPK(id);
		dao.delete(dtoExist);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CourseDTO findByName(String name) {
		CourseDTO dto = dao.findByName(name);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public CourseDTO findByPK(long pk) {
		CourseDTO dto = dao.findByPK(pk);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List search(CourseDTO dto) {
		return dao.search(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(CourseDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

}
