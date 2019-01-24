package com.raystech.proj0.form;

/**
 * Contains Reset Password form elements and their declarative input
 * validations.
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class ResetPasswordForm {
	/**
	 * Login Id of user
	 */
	private String login;

	/**
	 * New password of User
	 */
	private String newPassword;

	/**
	 * Confirm password of User
	 */
	private String confirmPassword;

	/**
	 * 
	 * accessors
	 */
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

}
