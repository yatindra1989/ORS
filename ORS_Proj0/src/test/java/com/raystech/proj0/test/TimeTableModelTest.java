package com.raystech.proj0.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.raystech.proj0.dto.TimeTableDTO;
import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.exception.ApplicationException;
import com.raystech.proj0.exception.DatabaseException;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.service.TimeTableServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class TimeTableModelTest {

	@Autowired
	TimeTableServiceInt service;

	@Test
	public void testAdd() throws ParseException, DuplicateRecordException {
		System.out.println("servicd" + service);

		try {
			TimeTableDTO dto = new TimeTableDTO();
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
			dto.setCourseId(1L);
			//dto.setSubjectId(4L);
			dto.setCourseName("testing");
			
			//dto.setExamTime("2pm-3pm");
			dto.setExamDate(sm.parse("18/04/17"));
			dto.setSubject("test");
			dto.setCreatedBy("ankit.gujrathi13@gmail.com");
			dto.setModifiedBy("ankit.gujrathi13@gmail.com");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));

			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

			System.out.println("going to add");
			long pk = service.add(dto);
			TimeTableDTO addeddto = service.findByPK(pk);
			if (addeddto == null) {
				System.out.println("Test add fail");
			} else {
				System.out.println("Successfully added");
			}

		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	@Ignore
	public void testUpdate() {
		try {
			TimeTableDTO dto = service.findByPK(3L);
			dto.setSubject("Zoology");
			service.update(dto);

			TimeTableDTO updateddto = service.findByPK(3L);
			if (!"DS".equals(updateddto.getSubject())) {
				System.out.println("Test Update fail");
			} else {
				System.out.println("success");
			}
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	@Ignore
	public void testList() throws ApplicationException {
		TimeTableDTO dto = new TimeTableDTO();
		List list = service.search(dto, 0, 0);
		if (list.size() < 0) {
			System.out.println("fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (TimeTableDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getSubject());
			//System.out.println(dto.getSubjectName());
			//System.out.println(dto.getSubjectId());
			System.out.println(dto.getCourseName());
			System.out.println(dto.getCourseId());
			System.out.println(dto.getExamDate());
			//System.out.println(dto.getExamTime());

		}

	}

	@Ignore
	public void testSearch() {

		TimeTableDTO dto = new TimeTableDTO();
		List list = new ArrayList();
		dto.setCourseId(4L);
		list = service.search(dto, 0, 0);
		if (list.size() < 0) {
			System.out.println("Test Serach fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (TimeTableDTO) it.next();
			System.out.print(dto.getId());
			System.out.print(dto.getCourseId());
			System.out.print(dto.getSubject());
			System.out.print(dto.getExamDate());
			System.out.print(dto.getCourseName());
			//System.out.print(dto.getSubjectName());
			//System.out.println(dto.getExamTime());

		}

	}

}
