package com.raystech.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raystech.proj0.dto.MarksheetDTO;
import com.raystech.proj0.dto.TimeTableDTO;
/**
 * Hibernate implementation of Marksheet DAO.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Repository("marksheetDAO")
public class MarksheetDAOHibImpl implements MarksheetDAOInt {
	/**
	 * Object of SessionFactory
	 */
	@Autowired
	SessionFactory sessionFactory = null;

	@Override
	public long add(MarksheetDTO dto) {
		return  (Long) sessionFactory.getCurrentSession().save(dto);
	}

	@Override
	public void update(MarksheetDTO dto) {
		sessionFactory.getCurrentSession().update(dto);
	}

	@Override
	public void delete(MarksheetDTO dto) {
		sessionFactory.getCurrentSession().delete(dto);
	}

	@Override
	public MarksheetDTO findByRollNo(String rollNo) {

		MarksheetDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(MarksheetDTO.class)
				.add(Restrictions.eq("rollNo", rollNo)).list();
		if (list.size() == 1) {
			dto = (MarksheetDTO) list.get(0);
		}
		return dto;
	}

	@Override
	public MarksheetDTO findByPK(long pk) {
		MarksheetDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(MarksheetDTO.class).add(Restrictions.eq("id", pk))
				.list();
		if (list.size() == 1) {
			dto = (MarksheetDTO) list.get(0);
		}
		return dto;
	}

	@Override
	public List search(MarksheetDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List search(MarksheetDTO dto, int pageNo, int pageSize) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MarksheetDTO.class);

		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getRollNo() != null && dto.getRollNo().length() > 0) {
				criteria.add(Restrictions.like("rollNo", dto.getRollNo() + "%"));
			}
			if (dto.getStudentId() > 0) {
				criteria.add(Restrictions.like("studentId", dto.getStudentId()));
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getPhysics() != null && dto.getPhysics() > 0) {
				criteria.add(Restrictions.like("physics", dto.getPhysics()));
			}
			if (dto.getPhysics() != null && dto.getChemistry() > 0) {
				criteria.add(Restrictions.like("chemistry", dto.getChemistry()));
			}
			if (dto.getMaths() != null && dto.getMaths() > 0) {
				criteria.add(Restrictions.like("physics", dto.getPhysics()));
			}
		}
		if (pageNo > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		return list;

	}

	@Override
	public List getMeritList(int pageNo, int pageSize) {
		List list = null;
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from MarksheetDTO order by ( physics + chemistry + maths) desc");

		query.setFirstResult(pageNo - 1);
		query.setMaxResults(pageSize);

		list = query.list();

		return list;
	}
}
