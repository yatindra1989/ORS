package com.raystech.proj0.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.exception.ApplicationException;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.service.UserServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class UserModelTest {

	@Autowired
	UserServiceInt service;

	@Test
	public void testAdd() throws ParseException, DuplicateRecordException {
		System.out.println("servicd" + service);
		System.out.println("in add method");

		try {
			UserDTO dto = new UserDTO();
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
			dto.setFirstName("ruchi");
			dto.setLastName("bharti");
			dto.setLogin("ruchi@gmail.com");
			dto.setPassword("12");
			dto.setDob(sm.parse("02/02/1994"));
			dto.setMobileNo("9837465783");
			dto.setRoleId(1L);
			//dto.setUnSuccessfulLogin(2);
			dto.setGender("female");
			dto.setLastLogin(new Timestamp(new Date().getTime()));
			dto.setLock("Yes");
			dto.setCreatedBy("ankit.gujrathi13@gmail.com");
			dto.setModifiedBy("ankit.gujrathi13@gmail.com");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = service.add(dto);
			UserDTO ud = service.findByPK(pk);
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
		UserDTO dto = new UserDTO();
		SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");

		dto.setId(4L);
		dto.setFirstName("prashan");
		dto.setLastName("solanki");
		dto.setRoleId(2L);
		dto.setLogin("prashan@gmail.com");
		dto.setPassword("11345");
		dto.setDob(sm.parse("13/02/1996"));
		dto.setMobileNo("8109175373");
		//dto.setUnSuccessfulLogin(2);
		dto.setGender("Male");
		dto.setLastLogin(new Timestamp(new Date().getTime()));
		dto.setLock("Yes");
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
		UserDTO dto = new UserDTO();
		/*
		 * long pk=3L; dto.setId(pk);
		 */
		service.delete(2L);
		System.out.println("done");
		// dto=service.findByPK(4L);
		/*
		 * if(dto!=null){ System.out.println("fail"); }else{
		 * System.out.println("success"); }
		 */
	}

	@Ignore
	public void testChangePassword() {
		Boolean cp = service.changePassword(3L, "4321", "1234");
		System.out.println("success");

	}

	@Ignore
	public void testForgetPassword() {

		String tes = service.forgetPassword("ankit.gujrathi13@gmail.com");
		System.out.println("success");

	}

	@Ignore
	public void testFindByLogin() {
		UserDTO dto = service.findByLogin("ankit.gujrathi13@gmail.com");
		System.out.println(dto.getFirstName());
		if (dto != null) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}

	}

	@Ignore
	public void testFindById() {
		UserDTO dto = service.findByPK(1l);
		System.out.println(dto.getFirstName());
	}

	@Ignore
	public void testSearch() {
		UserDTO dto = new UserDTO();
		dto.setFirstName("ankit");
		List list = service.search(dto, 0, 0);
		if (list.size() < 0) {
			System.out.println("search fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (UserDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getPassword());
			System.out.println(dto.getDob());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getRoleId());
			//System.out.println(dto.getUnSuccessfulLogin());
			System.out.println(dto.getGender());
			System.out.println(dto.getLastLogin());
			System.out.println(dto.getLock());
		}

	}

	@Ignore
	public void testList(){
		UserDTO dto=new UserDTO();
		List list=service.search(dto,0,0);
		if(list.size() < 0){
			System.out.println("fail");
		}
		Iterator it=list.iterator();
		while(it.hasNext()){
			dto=(UserDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getPassword());
			System.out.println(dto.getDob());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getRoleId());
			//System.out.println(dto.getUnSuccessfulLogin());
			System.out.println(dto.getGender());
			System.out.println(dto.getLastLogin());
			System.out.println(dto.getLock());
			
		}
	}
}
