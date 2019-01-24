package com.raystech.proj0.form;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.UserDTO;

/**
 * Contains generic attributes of a form. It is extended by all form beans.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class BaseForm {

	/**
	 * Contains non-business primary key
	 */
	protected long id = 0;
	/**
	 * Contains non-business primary key in long array
	 */
	private long[] ids;
	/**
	 * Current page number
	 */
	private int pageNo = 1;
	/**
	 * Number of records displayed on list page
	 */
	private int pageSize = 5;
	/**
	 * Operation User performed
	 */
	private String operation;
	/**
	 * Created by User ID
	 */
	protected String createdBy;
	/**
	 * Modified by User ID
	 */
	protected String modifiedBy;
	/**
	 * Record created datetime
	 */
	protected long createdDatetime;
	/**
	 * Record last modified datetime
	 */
	protected long modifiedDatetime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long[] getIds() {
		return ids;
	}

	public void setIds(long[] ids) {
		this.ids = ids;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public long getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(long createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public long getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(long modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	/**
	 * Converts form into dto.
	 * 
	 * @param session
	 * @return BaseDTO
	 */
	public BaseDTO getDto(HttpSession session) {
		return null;
	}

	/**
	 * Gets the generic attribute of User
	 * 
	 * @param session
	 */
	public void getGeneric(HttpSession session) {
		UserDTO userDto = (UserDTO) session.getAttribute("user");
		if (userDto == null) {
			if (createdBy == null || createdBy == "") {
				createdBy = "root";
			}
			modifiedBy = "root";
		} else {
			if (createdBy == null || createdBy == "") {
				createdBy = userDto.getLogin();
			}
			modifiedBy = userDto.getLogin();
		}
	}

	/**
	 * Converts dto into form to populate form
	 *
	 * @param bDto
	 */
	public void populate(BaseDTO bDto) {

	}
}
