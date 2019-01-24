package com.raystech.proj0.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.raystech.proj0.util.DataValidator;

@Component
public class ForgetPasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ForgetPasswordForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("In forgetPasswordValidator");
		ForgetPasswordForm form =  (ForgetPasswordForm)target;
		String newPassword = form.getNewPassword();
		String confirmPassword = form.getConfirmPassword();
		
		System.out.println("New Password is 1 :"+newPassword);
		
		if (DataValidator.isNull(newPassword)) {
			System.out.println("New Password is 2:"+newPassword);
		    errors.rejectValue("newPassword", "error.password.required");
		}
		
		if (DataValidator.isNull(confirmPassword)) {
		    errors.rejectValue("confirmPassword","error.confirmPassword.required");
		}
		
		if (!DataValidator.isNull(newPassword)) {
			if (!DataValidator.isPasslength(newPassword)) {
				errors.rejectValue("newPassword", "error.password");
			}
		}
		
		if (!DataValidator.isNull(confirmPassword)) {
			if (!newPassword.equals(confirmPassword) && !"".equals(confirmPassword)) {
				errors.rejectValue("confirmPassword", "error.confirmPassword");
			}
		}
		
	}

}
