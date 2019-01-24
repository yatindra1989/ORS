package com.raystech.proj0.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Contains Forget Password form elements and their declarative input
 * validations.
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class ForgetPasswordForm extends BaseForm {

	/**
	 * Login ID of User
	 */
	@NotEmpty
	@Email(message = "{error.login.invalid}")
	private String login;

	/**
	 * OTP value
	 */
	@NotEmpty
	private String otp;

	/**
	 * Password of User
	 */
	private String password;

	/**
	 * New Password of User
	 */
	private String newPassword;

	/**
	 * Confirm Password of User
	 */
	private String confirmPassword;

	/**
	 * 
	 * accessors
	 */

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	/*
	 * public ResetPasswordForm getResetForm() { return resetForm; }
	 * 
	 * public void setResetForm(ResetPasswordForm resetForm) { this.resetForm =
	 * resetForm; }
	 */

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
