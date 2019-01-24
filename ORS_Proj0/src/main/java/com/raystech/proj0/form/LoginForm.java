package com.raystech.proj0.form;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.raystech.proj0.dto.BaseDTO;
import com.raystech.proj0.dto.UserDTO;

/**
 * Contains Login form elements and their declarative input validations.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class LoginForm extends BaseForm {
	/**
	 * Login ID of User
	 */
	@NotEmpty(message = "{error.login.required}")
	@Email(message = "{error.login.invalid}")
	private String login;
	/**
	 * Password of User
	 */
	@NotEmpty(message = "{error.password.required}")
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public BaseDTO getDto(HttpSession session) {
		UserDTO dto = new UserDTO();
		dto.setLogin(login);
		dto.setPassword(password);
		return dto;
	}
}
