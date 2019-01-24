package com.raystech.proj0.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.raystech.proj0.util.DataValidator;

@Component
public class CollegeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CollegeForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CollegeForm collegeForm = (CollegeForm) target;
		String phoneNo = collegeForm.getPhoneNo();
		String name = collegeForm.getName();
		String state = collegeForm.getState();
		String city = collegeForm.getCity();

		if (!DataValidator.isNull(name)) {
			if (DataValidator.test(name.trim())) {
				errors.rejectValue("name", "error.college.invalid");
			}
		}

		if (!DataValidator.isNull(state)) {
			if (DataValidator.isState(state)) {
				errors.rejectValue("state", "error.state.invalid");
			}
		}
		if (!DataValidator.isNull(city)) {
			if (DataValidator.isState(city)) {
				errors.rejectValue("city", "error.city.invalid");
			}
		}
		if (!DataValidator.isNull(phoneNo)) {
			if (!DataValidator.isLong(phoneNo)) {
				errors.rejectValue("phoneNo", "error.phoneNo.number");
			} else if (phoneNo.length() < 10) {
				errors.rejectValue("phoneNo", "error.phoneNo.length");
			} else if (!DataValidator.isMob(phoneNo)) {
				errors.rejectValue("phoneNo", "error.phoneNo.invalid");
			}
		}

	}

}
