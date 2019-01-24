package com.raystech.proj0.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.raystech.proj0.util.DataValidator;

@Component
public class ChangePasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePasswordForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ChangePasswordForm form = (ChangePasswordForm) target;
		String newPassword = form.getNewPassword();
		String confirmPassword = form.getConfirmPassword();

		if (!DataValidator.isNull(newPassword)) {
			if (!DataValidator.isPasslength(newPassword)) {
				errors.rejectValue("newPassword", "error.password");
			}
		}
		if (!newPassword.equals(confirmPassword) && !"".equals(confirmPassword)) {
			errors.rejectValue("confirmPassword", "error.confirmAndNewPwd");
		}

	}

}
