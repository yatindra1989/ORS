package com.raystech.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raystech.proj0.dto.FacultyDTO;
import com.raystech.proj0.dto.TimeTableDTO;

/**
 * Hibernate implementation of Faculty DAO.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Repository("facultyDAO")
public class FacultyDAOHibImpl implements FacultyDAOInt {

	/**
	 * Object of SessionFactory
	 */
	@Autowired
	SessionFactory sessionFactory = null;

	@Override
	public long add(FacultyDTO dto) {
		long pk =  (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	@Override
	public void update(FacultyDTO dto) {
		sessionFactory.getCurrentSession().update(dto);
	}

	@Override
	public void delete(FacultyDTO dto) {
		sessionFactory.getCurrentSession().delete(dto);
	}

	@Override
	public FacultyDTO findByEmail(String email) {
		FacultyDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(FacultyDTO.class)
				.add(Restrictions.eq("emailId", email)).list();
		if (list.size() == 1) {
			dto = (FacultyDTO) list.get(0);
		}
		return dto;

	}

	@Override
	public FacultyDTO findByPK(long pk) {
		FacultyDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(FacultyDTO.class).add(Restrictions.eq("id", pk))
				.list();
		if (list.size() == 1) {
			dto = (FacultyDTO) list.get(0);
		}
		return dto;
	}

	@Override
	public List search(FacultyDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List search(FacultyDTO dto, int pageNo, int pageSize) {
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FacultyDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCollegeId() > 0) {
				criteria.add(Restrictions.eq("collegeId", dto.getCollegeId()));
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));
			}
			if (dto.getEmailId() != null && dto.getEmailId().length() > 0) {
				criteria.add(Restrictions.like("emailId", dto.getEmailId() + "%"));
			}
			if (dto.getMobileNo() != null && dto.getEmailId().length() > 0) {
				criteria.add(Restrictions.like("mobileNo", dto.getMobileNo() + "%"));
			}
			if (dto.getSubject() != null && dto.getSubject().length() > 0) {
				criteria.add(Restrictions.like("subject", dto.getSubject() + "%"));
			}
		}
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();
		return list;
	}

}
