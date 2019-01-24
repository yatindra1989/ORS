package com.raystech.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Role POJO class. It is persistent object.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Entity
@Table(name = "st_role")
public class RoleDTO extends BaseDTO {

	/**
	 * Constant for Admin ID
	 */
	public static final int ADMIN = 1;
	/**
	 * Constant for Student ID
	 */
	public static final int STUDENT = 2;
	/**
	 * Constant for College ID
	 */
	public static final int COLLEGE = 3;
	/**
	 * Constant for Kiosk ID
	 */
	public static final int KIOSK = 4;
	/**
	 * Constant for Faculty ID
	 */
	public static final int FACULTY = 5;

	/**
	 * Name of Role
	 */
	@Column(name = "NAME", length = 100)
	private String roleName;

	/**
	 * Description of Role
	 */
	@Column(name = "DESCRIPTION", length = 100)
	private String roleDescription;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return roleName;
	}

}
