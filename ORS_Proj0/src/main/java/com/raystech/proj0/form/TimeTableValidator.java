package com.raystech.proj0.form;

import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.raystech.proj0.util.DataValidator;
import com.raystech.proj0.util.Util;

@Component
public class TimeTableValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TimeTableForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TimeTableForm form = (TimeTableForm) target;
		long courseId = form.getCourseId();
		String subject = form.getSubject();
		String examDate = form.getExamDate();
		Date examDate2 = Util.getDate(examDate);

		if (courseId == 0) {
			errors.rejectValue("courseId", "error.courseName.required");
		}
		if (!DataValidator.isNull(subject)) {
			if (!DataValidator.isSubject(subject)) {
				errors.rejectValue("subject", "error.subject");
			}
		}

		if (!DataValidator.isNull(examDate)) {
			try {
				if (DataValidator.isExamDate(examDate)) {
					errors.rejectValue("examDate", "error.futureDate");
				} else if (examDate2.getDay() == 0) {
					errors.rejectValue("examDate", "error.exam.sunday");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

}
