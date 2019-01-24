package com.raystech.proj0.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
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

import com.raystech.proj0.dto.CourseDTO;
import com.raystech.proj0.dto.RoleDTO;
import com.raystech.proj0.service.CourseServiceInt;
import com.raystech.proj0.service.RoleServiceInt;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class CourseModelTest {

	
	@Autowired
	CourseServiceInt service;

	@Test
	public void testAdd() throws Exception {
		
		try {
			CourseDTO dto = new CourseDTO();
			dto.setCourseName("BCA");
			dto.setDescription("IT");
			dto.setCreatedBy("ruchi@gmail.com");
			dto.setModifiedBy("ruchi@gmail.com");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = service.add(dto);
			CourseDTO ud = service.findByPK(pk);
			System.out.println("success");
			if (ud == null) {
				System.out.println("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	}


