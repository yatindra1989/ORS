package com.raystech.proj0.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.raystech.proj0.dao.TimeTableDAOInt;
import com.raystech.proj0.dto.CourseDTO;
import com.raystech.proj0.dto.TimeTableDTO;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.util.Util;

/**
 * Time Table Service Spring implementation.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Service("timeTableService")
public class TimeTableServiceSpringImpl implements TimeTableServiceInt {
	/**
	 * Object of TimeTableDAOInt
	 */
	@Autowired
	TimeTableDAOInt dao;
	/**
	 * Object of CourseServiceInt
	 */
	@Autowired
	CourseServiceInt cservice;

	private static Logger log = Logger.getLogger(RoleServiceSpringImpl.class);

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(TimeTableDTO dto) throws DuplicateRecordException {
		Log.debug("TimeTableService add started");

		CourseDTO cdto = cservice.findByPK(dto.getCourseId());
		dto.setCourseName(cdto.getCourseName());

		TimeTableDTO courseSubjectExist = findBySubjectAndCourseId(dto.getSubject(), dto.getCourseId());
		TimeTableDTO courseDateExist = findByCourseIdAndDate(dto.getCourseId(), dto.getExamDate());
		if ((courseSubjectExist != null)) {
			throw new DuplicateRecordException(
					"Exam for this subject is already scheduled on " + Util.getDate(courseSubjectExist.getExamDate()));
		}
		if ((courseDateExist != null)) {
			throw new DuplicateRecordException(
					"Date is already assigned for " + courseDateExist.getSubject() + " Exam");
		}
		long pk = dao.add(dto);
		Log.debug("TimeTableService add end");
		return pk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TimeTableDTO dto) throws DuplicateRecordException {

		CourseDTO cdto = cservice.findByPK(dto.getCourseId());
		dto.setCourseName(cdto.getCourseName());

		TimeTableDTO courseSubjectExist = findBySubjectAndCourseId(dto.getSubject(), dto.getCourseId());
		TimeTableDTO courseDateExist = findByCourseIdAndDate(dto.getCourseId(), dto.getExamDate());

		if ((courseSubjectExist != null) && !(courseSubjectExist.getId() == dto.getId())) {
			throw new DuplicateRecordException(
					"Exam for this subject is already scheduled on " + Util.getDate(courseSubjectExist.getExamDate()));
		}
		if ((courseDateExist != null) && !(courseDateExist.getId() == dto.getId())) {
			throw new DuplicateRecordException(
					"Date is already assigned for " + courseDateExist.getSubject() + " Exam");
		}

		dao.update(dto);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		log.debug("TimeTable Service delete Start");
		TimeTableDTO dto = new TimeTableDTO();
		TimeTableDTO dtoExist = findByPK(id);
		dao.delete(dtoExist);
		log.debug("TimeTable Service delete End");
	}

	@Override
	@Transactional(readOnly = true)
	public TimeTableDTO findByName(String name) {
		log.debug("TimeTable Service findByName Started");
		TimeTableDTO dto = dao.findByName(name);
		log.debug("TimeTable Service findByName End");
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public TimeTableDTO findByPK(long pk) {
		log.debug("TimeTable Service findByPK Started");
		TimeTableDTO dto = dao.findByPK(pk);
		log.debug("TimeTable Service findByPK End");
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List search(TimeTableDTO dto) {
		return dao.search(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(TimeTableDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

	@Override
	@Transactional(readOnly = true)
	public TimeTableDTO findBySubjectAndCourseId(String subject, long courseId) {
		return dao.findBySubjectAndCourseId(subject, courseId);
	}

	@Override
	@Transactional(readOnly = true)
	public TimeTableDTO findByCourseIdAndDate(long courseId, Date date) {
		return dao.findByCourseIdAndDate(courseId, date);
	}

}
