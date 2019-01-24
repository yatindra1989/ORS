package com.raystech.proj0.test;

import java.sql.Timestamp;
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

import com.raystech.proj0.dto.RoleDTO;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.service.RoleServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class RoleModelTest {
	@Autowired
	RoleServiceInt service;

	@Test
	public void testAdd() throws Exception {
		System.out.println("servicd" + service);
		System.out.println("in add method");

		try {
			RoleDTO dto = new RoleDTO();
			dto.setRoleName("Ruchi");
			dto.setRoleDescription("Manager role");
			dto.setCreatedBy("ruchi@gmail.com");
			dto.setModifiedBy("ruchi@gmail.com");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = service.add(dto);
			RoleDTO ud = service.findByPK(pk);
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
		RoleDTO dto = new RoleDTO();

		dto.setId(6L);
		dto.setRoleName("abc");
		dto.setRoleDescription("abc role");
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
		RoleDTO dto = new RoleDTO();
		/*
		 * long pk=3L; dto.setId(pk);
		 */
		service.delete(6L);
		System.out.println("done");
		// dto=service.findByPK(4L);
		/*
		 * if(dto!=null){ System.out.println("fail"); }else{
		 * System.out.println("success"); }
		 */
	}

	@Ignore
	public void testFindById() {
		RoleDTO dto = service.findByPK(2L);
		System.out.println(dto.getRoleName());
	}

	@Ignore
	public void testSearch() {
		RoleDTO dto = new RoleDTO();
		dto.setRoleName("Admin");
		System.out.println(1);
		List list = service.search(dto, 0, 0);

		if (list.size() < 0) {
			System.out.println("search fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (RoleDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getRoleName());
			System.out.println(dto.getRoleDescription());

		}

	}

	@Test
	public void testList() {
		RoleDTO dto = new RoleDTO();
		List list = service.search(dto, 0, 0);
		if (list.size() < 0) {
			System.out.println("fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (RoleDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getRoleName());
			System.out.println(dto.getRoleDescription());

		}
	}
}
