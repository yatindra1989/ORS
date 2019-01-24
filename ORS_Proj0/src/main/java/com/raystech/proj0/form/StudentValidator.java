package com.raystech.proj0.form;

import java.text.ParseException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.raystech.proj0.util.DataValidator;

@Component
public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return StudentForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudentForm studentForm = (StudentForm) target;
		Long collegeId = studentForm.getCollegeId();
		String firstName = studentForm.getFirstName();
		String lastName = studentForm.getLastName();
		String emailId = studentForm.getEmailId();
		String dob = studentForm.getDob();
		String mobileNo = studentForm.getMobileNo();

		if (collegeId == 0) {
			errors.rejectValue("collegeId", "error.college.required");
		}
		if (!DataValidator.isNull(firstName)) {
			if (DataValidator.isWhiteSpace(firstName) || DataValidator.isSpecial(firstName)
					|| DataValidator.isNumber(firstName)) {
				if (DataValidator.isWhiteSpace(firstName) && DataValidator.isSpecial(firstName)
						&& DataValidator.isNumber(firstName)) {
					errors.rejectValue("firstName", "error.firstName.allthree");
				} else if (DataValidator.isWhiteSpace(firstName) && DataValidator.isSpecial(firstName)) {
					errors.rejectValue("firstName", "error.firstName.white&special");
				} else if (DataValidator.isWhiteSpace(firstName) && DataValidator.isNumber(firstName)) {
					errors.rejectValue("firstName", "error.firstName.white&number");
				} else if (DataValidator.isSpecial(firstName) && DataValidator.isNumber(firstName)) {
					errors.rejectValue("firstName", "error.firstName.special&number");
				} else if (DataValidator.isWhiteSpace(firstName)) {
					errors.rejectValue("firstName", "error.firstName.whiteSpace");
				} else if (DataValidator.isSpecial(firstName)) {
					errors.rejectValue("firstName", "error.firstName.special");
				} else if (DataValidator.isNumber(firstName)) {
					errors.rejectValue("firstName", "error.firstName.number");
				}
			}
		}
		if (!DataValidator.isNull(lastName)) {
			if (DataValidator.isSpecial(lastName) || DataValidator.isNumber(lastName)) {
				if (DataValidator.isSpecial(lastName) && DataValidator.isNumber(lastName)) {
					errors.rejectValue("lastName", "error.lastName.special&number");
				} else if (DataValidator.isSpecial(lastName)) {
					errors.rejectValue("lastName", "error.lastName.special");
				} else if (DataValidator.isNumber(lastName)) {
					errors.rejectValue("lastName", "error.lastName.number");
				}
			}

		}

		if (!DataValidator.isNull(dob)) {
			try {
				if (!DataValidator.isDOB(dob)) {
					errors.rejectValue("dob", "error.dob");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (!DataValidator.isNull(mobileNo)) {
			if (!DataValidator.isLong(mobileNo)) {
				errors.rejectValue("mobileNo", "error.mobileNo.number");
			} else if (mobileNo.length() < 10) {
				errors.rejectValue("mobileNo", "error.mobileNo.length");
			} else if (!DataValidator.isMob(mobileNo)) {
				errors.rejectValue("mobileNo", "error.mobileNo.invalid");
			}
		}

		if (!DataValidator.isNull(emailId)) {
			if (!DataValidator.isEmail(emailId)) {
				errors.rejectValue("emailId", "error.login");
			}
		}
	}

}
