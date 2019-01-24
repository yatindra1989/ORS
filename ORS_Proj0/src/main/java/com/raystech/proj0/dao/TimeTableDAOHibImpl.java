package com.raystech.proj0.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raystech.proj0.dto.RoleDTO;
import com.raystech.proj0.dto.TimeTableDTO;
/**
 * Hibernate implementation of TimeTable DAO.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Repository("timeTableDAO")
public class TimeTableDAOHibImpl implements TimeTableDAOInt {

	/**
	 * Object of SessionFactory
	 */
	@Autowired
	SessionFactory sessionFactory = null;

	private static Logger log = Logger.getLogger(TimeTableDAOHibImpl.class);

	@Override
	public long add(TimeTableDTO dto) {
		log.debug("TimeTable DAO add has started");
		long pk =  (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("TimeTable DAO add has ended");
		return pk;
	}

	@Override
	public void update(TimeTableDTO dto) {
		log.debug("TimeTable DAO update has started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("TimeTable DAO update has ended");

	}

	@Override
	public void delete(TimeTableDTO dto) {
		log.debug("TimeTable DAO delete has started");
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("TimeTable DAO delete has ended");

	}

	@Override
	public TimeTableDTO findByName(String subject) {
		log.debug("TimeTable DAO findByName has started");
		TimeTableDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(TimeTableDTO.class)
				.add(Restrictions.eq("subject", subject)).list();
		if (list.size() == 1) {
			dto = (TimeTableDTO) list.get(0);
		}
		log.debug("TimeTable DAO findByName has ended");
		return dto;
	}

	@Override
	public TimeTableDTO findByPK(long pk) {
		log.debug("TimeTable DAO findByPk has started");
		TimeTableDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(TimeTableDTO.class).add(Restrictions.eq("id", pk))
				.list();
		if (list.size() == 1) {
			dto = (TimeTableDTO) list.get(0);
		}
		log.debug("TimeTable DAO findByPk has ended");
		return dto;
	}

	@Override
	public List search(TimeTableDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List search(TimeTableDTO dto, int pageNo, int pageSize) {
		log.debug("TimeTable DAO search started");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TimeTableDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCourseId() > 0) {
				criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
			}
			if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
				criteria.add(Restrictions.like("courseName", dto.getCourseName() + "%"));
			}
			if (dto.getExamDate() != null && dto.getExamDate().getTime() > 0) {
				criteria.add(Restrictions.like("examDate", dto.getExamDate() + "%"));
			}
			if (dto.getSubject() != null && dto.getSubject().length() > 0) {
				criteria.add(Restrictions.like("subject", dto.getSubject() + "%"));
			}
		}
		if (pageNo > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		log.debug("TimeTable DAO search ended");
		return list;
	}

	@Override
	public TimeTableDTO findBySubjectAndCourseId(String subject, long courseId) {
		log.debug("TimeTableDAO findBySubjectAndCourseId started");
		TimeTableDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TimeTableDTO.class);
		if (courseId > 0) {
			criteria.add(Restrictions.eq("courseId", courseId));
		}
		if (subject != null && subject.length() > 0) {
			criteria.add(Restrictions.eq("subject", subject));
		}
		List list = criteria.list();
		if (list.size() > 0) {
			dto = (TimeTableDTO) list.get(0);
			sessionFactory.getCurrentSession().evict(dto);
		}
		log.debug("TimeTableDAO findBySubjectAndCourseId ended");
		return dto;
	}

	@Override
	public TimeTableDTO findByCourseIdAndDate(long courseId, Date date) {
		log.debug("TimeTableDAO findByCourseIdAndDate started");
		TimeTableDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TimeTableDTO.class);
		if (courseId > 0) {
			criteria.add(Restrictions.eq("courseId", courseId));
		}
		if (date != null && date.getTime() > 0) {
			criteria.add(Restrictions.eq("examDate", date));
		}
		List list = criteria.list();
		if (list.size() > 0) {
			dto = (TimeTableDTO) list.get(0);
			sessionFactory.getCurrentSession().evict(dto);
		}
		return dto;
	}

}
