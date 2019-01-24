package com.raystech.proj0.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.jdt.internal.compiler.ast.TryStatement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.raystech.proj0.dto.StudentDTO;
import com.raystech.proj0.dto.TimeTableDTO;
import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.exception.ApplicationException;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.service.StudentServiceInt;
import com.raystech.proj0.service.TimeTableServiceInt;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class StudentModelTest {
	@Autowired
	StudentServiceInt service;
	@Test
	public void testAdd() throws ParseException, DuplicateRecordException { {
		try{
	
		StudentDTO dto = new StudentDTO();
		SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
		dto.setCollegeId(1L);
		//dto.setSubjectId(4L);
		dto.setCollegeName("abc");
		dto.setDob(sm.parse("02/02/1994"));
		dto.setEmailId("a@gmail.com");
		//dto.setExamTime("2pm-3pm");
		dto.setFirstName("rb");
		dto.setLastName("bharti");
		dto.setMobileNo("987654323");
		dto.setCreatedBy("ankit.gujrathi13@gmail.com");
		dto.setModifiedBy("ankit.gujrathi13@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new java.util.Date().getTime()));

		dto.setModifiedDatetime(new Timestamp(new java.util.Date().getTime()));
		long pk = service.add(dto);
		StudentDTO ud = service.findByPK(pk);
		System.out.println("success");
		if (ud == null) {
			System.out.println("fail");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	}}}
