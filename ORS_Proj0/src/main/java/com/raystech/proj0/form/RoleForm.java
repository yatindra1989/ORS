package com.raystech.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.RoleDTO;

/**
 * Contains Role form elements and their declarative input validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class RoleForm extends BaseForm {
	/**
	 * Role Name
	 */
	@NotEmpty(message = "{error.roleName.required}")
	private String roleName;
	/**
	 * Role Description
	 */
	@NotEmpty(message = "{error.roleDescription.required}")
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
	public BaseDTO getDto(HttpSession session) {

		RoleDTO dto = new RoleDTO();
		dto.setId(id);
		dto.setRoleName(roleName);
		dto.setRoleDescription(roleDescription);
		getGeneric(session);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		if (createdDatetime > 0) {
			System.out.println("11");
			dto.setCreatedDatetime(new Timestamp(createdDatetime));
		} else {
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		}
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		return dto;

	}

	@Override
	public void populate(BaseDTO bDto) {
		if (bDto == null) {
			return;
		}
		RoleDTO dto = (RoleDTO) bDto;

		id = dto.getId();
		roleName = dto.getRoleName();
		roleDescription = dto.getRoleDescription();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}

}
