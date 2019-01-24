package com.raystech.proj0.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.raystech.proj0.dto.RoleDTO;
import com.raystech.proj0.util.DataValidator;

@Component
public class RoleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RoleForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RoleForm roleForm = (RoleForm) target;
		String roleName = roleForm.getRoleName();

		if (!DataValidator.isNull(roleName)) {
			if (DataValidator.isWhiteSpace(roleName) || DataValidator.isSpecial(roleName)
					|| DataValidator.isNumber(roleName)) {
				if (DataValidator.isWhiteSpace(roleName) && DataValidator.isSpecial(roleName)
						&& DataValidator.isNumber(roleName)) {
					errors.rejectValue("roleName", "error.roleName.allthree");
				} else if (DataValidator.isWhiteSpace(roleName) && DataValidator.isSpecial(roleName)) {
					errors.rejectValue("roleName", "error.roleName.white&special");
				} else if (DataValidator.isWhiteSpace(roleName) && DataValidator.isNumber(roleName)) {
					errors.rejectValue("roleName", "error.roleName.white&number");
				} else if (DataValidator.isSpecial(roleName) && DataValidator.isNumber(roleName)) {
					errors.rejectValue("roleName", "error.roleName.special&number");
				} else if (DataValidator.isWhiteSpace(roleName)) {
					errors.rejectValue("roleName", "error.roleName.whiteSpace");
				} else if (DataValidator.isSpecial(roleName)) {
					errors.rejectValue("roleName", "error.roleName.special");
				} else if (DataValidator.isNumber(roleName)) {
					errors.rejectValue("roleName", "error.roleName.number");
				}
			}
		}
	}

}
