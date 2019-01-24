package com.raystech.proj0.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.raystech.proj0.dto.FacultyDTO;
import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.service.FacultyServiceInt;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class FacultyModelTest {

@Autowired
FacultyServiceInt service;	
@Test
public void testAdd() throws ParseException, DuplicateRecordException {
	System.out.println("servicd" + service);
	System.out.println("in add method");

	try {
		FacultyDTO dto = new FacultyDTO();
		SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
		dto.setCollegeId(1L);
		dto.setCollegeName("Bansal");
		
		dto.setDob(sm.parse("02/02/1994"));
		dto.setEmailId("r@gmail.com");
		dto.setMobileNo("9837465783");
	dto.setFirstName("ruchi");
	dto.setLastName("bahrti");
	dto.setMobileNo("96385740");
		
		dto.setCreatedBy("ankit.gujrathi13@gmail.com");
		dto.setModifiedBy("ankit.gujrathi13@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long pk = service.add(dto);
		FacultyDTO ud = service.findByPK(pk);
		System.out.println("success");
		if (ud == null) {
			System.out.println("fail");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

}
}
