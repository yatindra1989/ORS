package com.raystech.proj0.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.raystech.proj0.util.DataValidator;

@Component
public class CourseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CourseForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CourseForm form = (CourseForm) target;
		String courseName = form.getCourseName();

		if (!DataValidator.isNull(courseName)) {
			if (!DataValidator.isCourse(courseName)) {
				errors.rejectValue("courseName", "error.course.invalid");
			}
		}

	}

}
