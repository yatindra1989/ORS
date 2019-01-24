package com.raystech.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raystech.proj0.dto.StudentDTO;
import com.raystech.proj0.dto.UserDTO;
/**
 * Hibernate implementation of Student DAO.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Repository("studentDAO")
public class StudentDAOHibImpl implements StudentDAOInt {

	/**
	 * Object of SessionFactory
	 */
	@Autowired
	SessionFactory sessionFactory = null;

	@Override
	public long add(StudentDTO dto) {
		long pk =  (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	@Override
	public void update(StudentDTO dto) {
		sessionFactory.getCurrentSession().update(dto);
	}

	@Override
	public void delete(StudentDTO dto) {
		sessionFactory.getCurrentSession().delete(dto);

	}

	@Override
	public StudentDTO findByEmail(String emailId) {
		StudentDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(StudentDTO.class)
				.add(Restrictions.eq("emailId", emailId)).list();
		if (list.size() == 1) {
			dto = (StudentDTO) list.get(0);
		}
		return dto;
	}

	@Override
	public StudentDTO findByPK(long pk) {
		StudentDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(StudentDTO.class).add(Restrictions.eq("id", pk))
				.list();
		if (list.size() == 1) {
			dto = (StudentDTO) list.get(0);
		}
		return dto;

	}

	@Override
	public List search(StudentDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List search(StudentDTO dto, int pageNo, int pageSize) {
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
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
			if (dto.getDob() != null && dto.getDob().getDate() > 0) {
				criteria.add(Restrictions.like("dob", dto.getDob()));
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				criteria.add(Restrictions.like("mobileNo", dto.getMobileNo() + "%"));
			}
			if (dto.getCollegeId() > 0) {
				criteria.add(Restrictions.eq("collegeId", dto.getCollegeId()));
			}
		}
		if (pageNo > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();

		return list;

	}

}
