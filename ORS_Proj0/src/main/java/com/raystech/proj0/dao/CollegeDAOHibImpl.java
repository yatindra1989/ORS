package com.raystech.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raystech.proj0.dto.CollegeDTO;
import com.raystech.proj0.dto.TimeTableDTO;

/**
 * Hibernate implementation of College DAO.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Repository("collegeDao")
public class CollegeDAOHibImpl implements CollegeDAOInt {

	/**
	 * Object of SessionFactory
	 */
	@Autowired
	SessionFactory sessionFactory = null;

	@Override
	public long add(CollegeDTO dto) {
		long pk =  (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	@Override
	public void update(CollegeDTO dto) {
		sessionFactory.getCurrentSession().update(dto);

	}

	@Override
	public void delete(CollegeDTO dto) {
		sessionFactory.getCurrentSession().delete(dto);

	}

	@Override
	public CollegeDTO findByName(String name) {
		CollegeDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(CollegeDTO.class)
				.add(Restrictions.eq("name", name)).list();
		if (list.size() == 1) {
			dto = (CollegeDTO) list.get(0);
		}
		return dto;
	}

	@Override
	public CollegeDTO findByPK(long pk) {
		CollegeDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(CollegeDTO.class).add(Restrictions.eq("id", pk))
				.list();
		if (list.size() == 1) {
			dto = (CollegeDTO) list.get(0);
		}
		return dto;
	}

	@Override
	public List search(CollegeDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List search(CollegeDTO dto, int pageNo, int pageSize) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CollegeDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getAddress() != null && dto.getAddress().length() > 0) {
				criteria.add(Restrictions.like("address", dto.getAddress() + "%"));
			}
			if (dto.getState() != null && dto.getState().length() > 0) {
				criteria.add(Restrictions.like("state", dto.getState() + "%"));
			}
			if (dto.getCity() != null && dto.getCity().length() > 0) {
				criteria.add(Restrictions.like("city", dto.getCity() + "%"));
			}
		}
		if (pageNo > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		return list;

	}

}
