package com.raystech.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raystech.proj0.dto.CourseDTO;
import com.raystech.proj0.dto.RoleDTO;

/**
 * Hibernate implementation of Course DAO.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Repository("courseDAO")
public class CourseDAOHibImpl implements CourseDAOInt {

	/**
	 * Object of SessionFactory
	 */
	@Autowired
	SessionFactory sessionFactory = null;

	@Override
	public long add(CourseDTO dto) {
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	@Override
	public void update(CourseDTO dto) {
		sessionFactory.getCurrentSession().update(dto);
	}

	@Override
	public void delete(CourseDTO dto) {
		sessionFactory.getCurrentSession().delete(dto);
	}

	@Override
	public CourseDTO findByName(String name) {
		CourseDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CourseDTO.class);
		List list = criteria.add(Restrictions.eq("courseName", name)).list();
		if (list.size() == 1) {
			dto = (CourseDTO) list.get(0);
		}
		return dto;
	}

	@Override
	public CourseDTO findByPK(long pk) {
		CourseDTO dto = null;
		System.out.println("1");
		List list = sessionFactory.getCurrentSession().createCriteria(CourseDTO.class).add(Restrictions.eq("id", pk))
				.list();
		System.out.println("2");
		if (list.size() == 1) {
			System.out.println("3");
			dto = (CourseDTO) list.get(0);
		}
		System.out.println("4");
		return dto;
	}

	@Override
	public List search(CourseDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List search(CourseDTO dto, int pageNo, int pageSize) {
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CourseDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
				criteria.add(Restrictions.like("courseName", dto.getCourseName() + "%"));
			}
			if (dto.getDescription() != null && dto.getDescription().length() > 0) {
				criteria.add(Restrictions.like("description", dto.getDescription() + "%"));
			}
		}

		// if page size is greater than zero the apply pagination
		if (pageNo > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();
		return list;

	}

}
