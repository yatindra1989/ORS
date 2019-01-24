package com.raystech.proj0.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.raystech.proj0.dao.MarksheetDAOInt;
import com.raystech.proj0.dto.MarksheetDTO;
import com.raystech.proj0.dto.StudentDTO;
import com.raystech.proj0.exception.DuplicateRecordException;

/**
 * Marksheet Service Spring implementation.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service("marksheetService")
public class MarksheetServiceSpringImpl implements MarksheetServiceInt {
	/**
	 * Object of MarksheetDAOINt
	 */
	@Autowired
	MarksheetDAOInt dao;
	/**
	 * Object of StudentServiceInt
	 */
	@Autowired
	StudentServiceInt service;

	@Autowired
	private JavaMailSenderImpl mailSender;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(MarksheetDTO dto) throws DuplicateRecordException {

		StudentDTO studentDTO = service.findByPK(dto.getStudentId());
		String firstName = studentDTO.getFirstName();
		String lastName = studentDTO.getLastName();
		dto.setName(firstName + " " + lastName);

		MarksheetDTO existDTO = dao.findByRollNo(dto.getRollNo());
		if (existDTO != null) {
			throw new DuplicateRecordException("Roll Number already exist");
		}

		long pk = dao.add(dto);
		return pk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(MarksheetDTO dto) {
		StudentDTO studentDTO = service.findByPK(dto.getStudentId());
		String firstName = studentDTO.getFirstName();
		String lastName = studentDTO.getLastName();
		dto.setName(firstName + " " + lastName);
		dao.update(dto);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long pk) {
		MarksheetDTO dto = new MarksheetDTO();
		MarksheetDTO dtoExist = findByPK(pk);
		dao.delete(dtoExist);

	}

	@Override
	@Transactional(readOnly = true)
	public MarksheetDTO findByRollNo(String rollNo) {
		MarksheetDTO dto = dao.findByRollNo(rollNo);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public MarksheetDTO findByPK(long pk) {
		MarksheetDTO dto = dao.findByPK(pk);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List search(MarksheetDTO dto) {
		return dao.search(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(MarksheetDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

	@Override
	@Transactional(readOnly = true)
	public List getMeritList(int pageNo, int pageSize) {
		return dao.getMeritList(pageNo, pageSize);
	}

	@Override
	@Transactional(readOnly = true)
	public void sendMarksheetOnEmail(String rollNo, String email) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(email);
			helper.setSubject("Marksheet : " + rollNo);
			helper.setText("Dear Student, Please find the Marksheet attached");
			FileSystemResource file = new FileSystemResource(
					"E:/ORS_Proj0/ORS_Proj0/src/main/webapp/resources/marksheetPDF/" + rollNo + ".pdf");
			helper.addAttachment(file.getFilename(), file);

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
	}

}
