package com.raystech.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raystech.proj0.dto.RoleDTO;

/**
 * Hibernate implementation of Role DAO.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */

@Repository("roleDAO")
public class RoleDAOHibImpl implements RoleDAOInt {
	/**
	 * Object of SessionFactory
	 */
	@Autowired
	SessionFactory sessionFactory = null;

	private static Logger log = Logger.getLogger(RoleDAOHibImpl.class);

	@Override
	public long add(RoleDTO dto) {
		log.debug("Role Dao Add started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("Role Dao Add End");
		return pk;
	}

	@Override
	public void update(RoleDTO dto) {
		log.debug("Role Dao Update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("Role Dao Update End");
	}

	@Override
	public void delete(RoleDTO dto) {
		log.debug("Role Dao Delete Started");
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("Role Dao Delete End");
	}

	@Override
	public RoleDTO findByName(String roleName) {
		log.debug("RoleDAO findByName Started");
		RoleDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class)
				.add(Restrictions.eq("roleName", roleName)).list();
		if (list.size() == 1) {
			dto = (RoleDTO) list.get(0);
		}
		log.debug("RoleDAO findByName End");
		return dto;
	}

	@Override
	public RoleDTO findByPK(long pk) {
		log.debug("RoleDAO findByPK started");
		RoleDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class).add(Restrictions.eq("id", pk))
				.list();
		if (list.size() == 1) {
			dto = (RoleDTO) list.get(0);
		}
		log.debug("RoleDAO findByPK end");
		return dto;
	}

	@Override
	public List search(RoleDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List search(RoleDTO dto, int pageNo, int pageSize) {
		log.debug("RoleDAO search method Started");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getRoleName() != null && dto.getRoleName().length() > 0) {
				criteria.add(Restrictions.like("roleName", dto.getRoleName() + "%"));
			}
			if (dto.getRoleDescription() != null && dto.getRoleDescription().length() > 0) {
				criteria.add(Restrictions.like("roleDescription", dto.getRoleDescription() + "%"));
			}
		}

		// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}

		List list = criteria.list();
		log.debug("RoleDAO search method End");
		return list;
	}

}
