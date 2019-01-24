package com.raystech.proj0.form;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.UserDTO;

/**
 * Contains ChangePassword form elements and their declarative input
 * validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class ChangePasswordForm extends BaseForm {
	/**
	 * Old Password of User
	 */
	@NotEmpty(message = "{error.oldPassword.required}")
	private String oldPassword;
	/**
	 * New Password of User
	 */
	@NotEmpty(message = "{error.newPassword.required}")
	private String newPassword;
	/**
	 * Confirm Password of User
	 */
	@NotEmpty(message = "{error.confirmPassword.required}")
	private String confirmPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public BaseDTO getDto(HttpSession session) {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(new Timestamp(createdDatetime));
		dto.setModifiedDatetime(new Timestamp(modifiedDatetime));
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		UserDTO dto = (UserDTO) bDto;
		id = dto.getId();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}

}
