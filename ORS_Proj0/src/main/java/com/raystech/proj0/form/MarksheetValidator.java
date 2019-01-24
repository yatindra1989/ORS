package com.raystech.proj0.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.raystech.proj0.util.DataValidator;

@Component
public class MarksheetValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MarksheetForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MarksheetForm marksheetForm = (MarksheetForm) target;
		String rollNo = marksheetForm.getRollNo();
		long studentId = marksheetForm.getStudentId();
		String physics = marksheetForm.getPhysics();
		String chemistry = marksheetForm.getChemistry();
		String maths = marksheetForm.getMaths();

		if (studentId == 0) {
			errors.rejectValue("studentId", "error.name.required");
		}

		if (!DataValidator.isNull(rollNo)) {
			if (rollNo.length() > 14) {
				errors.rejectValue("rollNo", "error.rollNo.maxLen");
			} else if (rollNo.length() < 5) {
				errors.rejectValue("rollNo", "error.rollNo.minLen");
			} else if (!DataValidator.isRollNo(rollNo)) {
				errors.rejectValue("rollNo", "error.rollNo.format");
			}
		}

		if (!DataValidator.isNull(physics)) {
			if (!DataValidator.isInteger(physics)) {
				errors.rejectValue("physics", "error.marks.integer");
			} else if ((Integer.parseInt(physics)) > 100) {
				errors.rejectValue("physics", "error.marks.max");
			} else if ((Integer.parseInt(physics)) < 0) {
				errors.rejectValue("physics", "error.marks.min");
			}
		}
		if (!DataValidator.isNull(chemistry)) {
			if (!DataValidator.isInteger(String.valueOf(chemistry))) {
				errors.rejectValue("chemistry", "error.marks.integer");
			} else if ((Integer.parseInt(chemistry)) > 100) {
				errors.rejectValue("chemistry", "error.marks.max");
			} else if ((Integer.parseInt(chemistry)) < 0) {
				errors.rejectValue("chemistry", "error.marks.min");
			}
		}
		if (!DataValidator.isNull(maths)) {
			if (!DataValidator.isInteger(String.valueOf(maths))) {
				errors.rejectValue("maths", "error.marks.integer");
			} else if ((Integer.parseInt(maths)) > 100) {
				errors.rejectValue("maths", "error.marks.max");
			} else if ((Integer.parseInt(maths)) < 0) {
				errors.rejectValue("maths", "error.marks.min");
			}
		}

	}

}
