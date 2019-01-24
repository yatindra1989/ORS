package com.raystech.proj0.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.raystech.proj0.dto.CollegeDTO;
import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.service.CollegeServiceInt;
import com.raystech.proj0.service.UserServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class CollegeModelTest {
	@Autowired
	CollegeServiceInt service;

	@Test
	public void testAdd() throws ParseException, DuplicateRecordException {
		System.out.println("servicd" + service);
		System.out.println("in add method");

		try {
			CollegeDTO dto = new CollegeDTO();
			dto.setName("SVM");
			dto.setCity("khargone");
			dto.setAddress("Khargone");
			dto.setPhoneNo("8765456765");
			dto.setState("M.P.");
			dto.setCreatedBy("ankit.gujrathi13@gmail.com");
			dto.setModifiedBy("ankit.gujrathi13@gmail.com");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = service.add(dto);
			CollegeDTO ud = service.findByPK(pk);
			System.out.println("success");
			if (ud == null) {
				System.out.println("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Ignore
	public void testUpdate() throws Exception {
		CollegeDTO dto = new CollegeDTO();
		dto.setId(24L);
		dto.setName("P.G.");
		dto.setCity("khargone");
		dto.setAddress("Khargone");
		dto.setPhoneNo("8765456765");
		dto.setState("M.P.");
		dto.setCreatedBy("ankit.gujrathi13@gmail.com");
		dto.setModifiedBy("ankit.gujrathi13@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			service.update(dto);
			System.out.println("update scess");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testDelete() {
		CollegeDTO dto = new CollegeDTO();
		/*
		 * long pk=3L; dto.setId(pk);
		 */
		service.delete(24L);
		System.out.println("done");
		// dto=service.findByPK(4L);
		/*
		 * if(dto!=null){ System.out.println("fail"); }else{
		 * System.out.println("success"); }
		 */
	}

	@Ignore
	public void testFindByPK() {
		CollegeDTO dto = service.findByPK(23L);
		System.out.println(dto.getName());
	}

	@Ignore
	public void testSearch() {
		CollegeDTO dto = new CollegeDTO();
		dto.setName("IPM");
		List list = service.search(dto, 0, 0);
		if (list.size() < 0) {
			System.out.println("search fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (CollegeDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getCity());
			System.out.println(dto.getAddress());
			System.out.println(dto.getState());
		}

	}

	@Test
	public void testList() {
		CollegeDTO dto = new CollegeDTO();
		List list = service.search(dto, 0, 0);
		if (list.size() < 0) {
			System.out.println("fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (CollegeDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getCity());
			System.out.println(dto.getAddress());
			System.out.println(dto.getCity());

		}
	}

	@Ignore
	public void testFindbyName() {
		CollegeDTO dto = service.findByName("IPS");
		System.out.println(dto.getName());
		System.out.println(dto.getCity());
		System.out.println(dto.getAddress());
		System.out.println(dto.getState());

	}
}
