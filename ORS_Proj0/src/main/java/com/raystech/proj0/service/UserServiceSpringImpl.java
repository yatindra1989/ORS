package com.raystech.proj0.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.raystech.proj0.dao.UserDAOInt;
import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.util.EmailBuilder;

/**
 * User Service Spring implementation.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service(value = "userService")
public class UserServiceSpringImpl implements UserServiceInt {
	/**
	 * Object of UserDAOInt
	 */
	@Autowired
	private UserDAOInt dao = null;
	/**
	 * Object of JavaMailSenderImpl
	 */
	@Autowired
	private JavaMailSenderImpl mailSender;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void setDao(UserDAOInt dao) {
		this.dao = dao;
	}

	private static Logger log = Logger.getLogger(UserServiceSpringImpl.class);

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(UserDTO dto) throws DuplicateRecordException {
		UserDTO dtoExist = dao.findByLogin(dto.getLogin());
		if (dtoExist != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		long pk = dao.add(dto);
		return pk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long registerUser(UserDTO dto) throws DuplicateRecordException, MessagingException {

		long id = add(dto);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		MimeMessage msg = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(msg);

		helper.setTo(dto.getLogin());

		helper.setSubject("Registration is successful for ORS Project SUNRAYS Technologies");
		// use the true flag to indicate the text included is HTML
		helper.setText(message, true);
		mailSender.send(msg);

		return id;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(UserDTO dto) {
		dao.update(dto);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		UserDTO dtoExist = findByPK(id);
		dao.delete(dtoExist);

	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findByLogin(String login) {
		UserDTO dto = dao.findByLogin(login);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findByPK(long id) {
		UserDTO dto = dao.findByPK(id);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List search(UserDTO dto) {
		return dao.search(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(UserDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean changePassword(Long id, String oldPassword, String newPassword) {
		UserDTO dto = findByPK(id);
		if (oldPassword.equals(dto.getPassword())) {
			dto.setPassword(newPassword);
			dao.update(dto);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO authenticate(UserDTO dto) {
		UserDTO dtoExist = null;
		dtoExist = dao.authenticate(dto);
		if (dtoExist != null && dtoExist.getPassword() == dto.getPassword()) {
			// Set last login date
			dtoExist.setLastLogin(new Timestamp(new Date().getTime()));
			dao.update(dtoExist);
			return dtoExist;
		}
		return dtoExist;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public boolean lock(String login) {
		log.debug("Service lock Started");
		boolean flag = false;
		UserDTO dtoExist = null;
		dtoExist = findByLogin(login);
		if (dtoExist != null) {
			dtoExist.setLock(UserDTO.ACTIVE);
			dao.update(dtoExist);
			flag = true;
		} else {
		}
		log.debug("Service lock End");
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public UserDTO updateAccess(UserDTO dto) {
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String forgetPassword(String login) {
		log.debug("UserService forgetPassword started");
		UserDTO dtoExist = dao.findByLogin(login);

		Long l = 0L;
		String otp = "";

		while (otp.length() != 6) {
			l = (long) (Math.random() * 1000000);
			otp = String.valueOf(l);
		}
		System.out.println("in generateOTP otp is:" + otp);

		if (dtoExist != null) {
			System.out.println("In User Service dto is not null");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("firstName", dtoExist.getFirstName());
			map.put("lastName", dtoExist.getLastName());
			map.put("login", dtoExist.getLogin());
			map.put("otp", otp);

			String message = EmailBuilder.getForgetPasswordMessage(map);

			MimeMessage msg = mailSender.createMimeMessage();

			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg);
				helper.setTo(login);
				helper.setSubject("SunRays ORS OTP to reset your password");
				// use the true flag to indicate the text included is HTML
				helper.setText(message, true);
				mailSender.send(msg);

			} catch (MessagingException e) {
				e.printStackTrace();
				log.error(e);
				return null;
			}

		} else {
			return null;
		}

		log.debug("UserService forgetPassword ended");

		return otp;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public boolean resetPassword(String login, String newPassword) {
		log.debug("Service resetPassword Started");
		boolean flag = false;
		UserDTO dtoExist = null;
		dtoExist = dao.findByLogin(login);
		if (dtoExist != null) {
			dtoExist.setPassword(newPassword);
			dao.update(dtoExist);

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("login", dtoExist.getLogin());
			map.put("password", dtoExist.getPassword());
			map.put("firstName", dtoExist.getFirstName());
			map.put("lastName", dtoExist.getLastName());
			String message = EmailBuilder.getChangePasswordMessage(map);

			MimeMessage msg = mailSender.createMimeMessage();

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper;
			try {
				helper = new MimeMessageHelper(msg, true);
				helper.setTo(dtoExist.getLogin());
				helper.setSubject("Password has been reset.");
				// use the true flag to indicate the text included is HTML
				helper.setText(message, true);
			} catch (MessagingException e) {
				System.out.println("Mail Sending Failed");
				e.printStackTrace();
			}
			mailSender.send(msg);

			flag = true;
		} else {
		}
		log.debug("Service restPassword End");
		return flag;
	}

}
