package com.raystech.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raystech.proj0.dto.UserDTO;

/**
 * Hibernate implementation of User DAO.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Repository("userDao")
public class UserDAOHibImpl implements UserDAOInt {

	/**
	 * Object of SessionFactory
	 */
	@Autowired
	SessionFactory sessionFactory = null;

	private static Logger log = Logger.getLogger(UserDAOHibImpl.class);

	@Override
	public long add(UserDTO dto) {
		Log.debug("UserDAO add method started");
		Log.debug("UserDAO add method end");
		long pk =  (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	@Override
	public void update(UserDTO dto) {
		Log.debug("UserDAO update method started");
		sessionFactory.getCurrentSession().update(dto);
		Log.debug("UserDAO update method end");
	}

	@Override
	public void delete(UserDTO dto) {
		Log.debug("UserDAO delete method started");
		sessionFactory.getCurrentSession().delete(dto);
		Log.debug("UserDAO delete method end");
	}

	@Override
	public UserDTO findByLogin(String login) {
		UserDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(UserDTO.class)
				.add(Restrictions.eq("login", login)).list();
		if (list.size() == 1) {
			dto = (UserDTO) list.get(0);
		}
		return dto;
	}

	@Override
	public UserDTO findByPK(long pk) {
		UserDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(UserDTO.class).add(Restrictions.eq("id", pk))
				.list();
		if (list.size() == 1) {
			dto = (UserDTO) list.get(0);
		}
		return dto;
	}

	@Override
	public List search(UserDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List search(UserDTO dto, int pageNo, int pageSize) {
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserDTO.class);
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
			if (dto.getLogin() != null && dto.getLogin().length() > 0) {
				criteria.add(Restrictions.like("login", dto.getLogin() + "%"));
			}
			if (dto.getPassword() != null && dto.getPassword().length() > 0) {
				criteria.add(Restrictions.like("password", dto.getPassword() + "%"));
			}
			if (dto.getDob() != null && dto.getDob().getDate() > 0) {
				criteria.add(Restrictions.like("dob", dto.getDob()));
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				criteria.add(Restrictions.like("mobileNo", dto.getMobileNo() + "%"));
			}
			if (dto.getRoleId() > 0) {
				criteria.add(Restrictions.eq("roleId", dto.getRoleId()));
			}
			if (dto.getGender() != null && dto.getGender().length() > 0) {
				criteria.add(Restrictions.like("gender", dto.getGender() + "%"));
			}
		}
		if (pageNo > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		list = criteria.list();

		return list;

	}

	@Override
	public UserDTO authenticate(UserDTO dto) {
		UserDTO dto1 = null;
		List list = sessionFactory.getCurrentSession().createCriteria(UserDTO.class)
				.add(Restrictions.eq("login", dto.getLogin())).add(Restrictions.eq("password", dto.getPassword()))
				.list();
		if (list.size() == 1) {
			dto1 = (UserDTO) list.get(0);
		}

		log.debug("User DAO Find by Name Ended");
		return dto1;

	}

}
