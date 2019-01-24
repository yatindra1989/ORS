package com.raystech.proj0.ctl;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.raystech.proj0.dto.RoleDTO;
import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.exception.ApplicationException;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.form.ForgetPasswordForm;
import com.raystech.proj0.form.ForgetPasswordValidator;
import com.raystech.proj0.form.LoginForm;
import com.raystech.proj0.form.ResetPasswordForm;
import com.raystech.proj0.form.UserRegistrationForm;
import com.raystech.proj0.form.UserRegistrationValidator;
import com.raystech.proj0.service.RoleServiceInt;
import com.raystech.proj0.service.UserServiceInt;

/**
 * Contains navigation logics for Login, Forgot Password and SignUp Usecases.
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Controller
public class LoginCtl extends BaseCtl {

	/**
	 * Login id for Forget Password operation
	 */
	String loginId = "";

	/**
	 * OTP for Forget Password operation
	 */
	String otp = "";

	/**
	 * Operation constant for SignIN
	 */
	protected static final String OP_SIGNIN = "SignIn";
	/**
	 * Operation constant for SignUp
	 */
	protected static final String OP_SIGNUP = "SignUp";

	/**
	 * Object of UserServiceInt
	 */
	@Autowired
	private UserServiceInt service;

	/**
	 * Object of RoleServiceInt
	 */
	@Autowired
	private RoleServiceInt roleService;

	/**
	 * Object of UserRegistrationValidator
	 */
	@Autowired
	UserRegistrationValidator userRegistrationValidator;

	/**
	 * Object of ForgetPasswordValidator
	 */
	@Autowired
	ForgetPasswordValidator forgetPasswordValidator;

	/**
	 * Object of MessageSource
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Displays Login view
	 * 
	 * @param form
	 * @param session
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") LoginForm form, HttpSession session,
			@RequestParam(required = false) String success, Model model) {

		model.addAttribute("success", success);
		System.out.println("Success is:" + success);
		session.invalidate();

		return "Login";
	}

	/**
	 * Submits Login view data
	 * 
	 * @param locale
	 * @param form
	 * @param bindingResult
	 * @param session
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String submit(Locale locale, @ModelAttribute("form") @Valid LoginForm form, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (OP_SIGNIN.equalsIgnoreCase(form.getOperation())) {
			if (bindingResult.hasErrors()) {
				return "Login";
			}
			UserDTO dto = (UserDTO) form.getDto(session);
			dto = service.authenticate(dto);
			if (dto != null) {
				RoleDTO roleDTO = roleService.findByPK(dto.getRoleId());
				session.setAttribute("role", roleDTO.getRoleName());
				session.setAttribute("user", dto);
				model.addAttribute("message", "Welcome : " + form.getLogin());
				return "redirect:/Welcome";
			} else {
				String msg = messageSource.getMessage("login.error", null, locale);
				model.addAttribute("error", msg);
			}
		}
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/Login";
		}

		return "Login";
	}

	@RequestMapping(value = "/ForgetPassword", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") ForgetPasswordForm form, Model model) {
		return "ForgetPassword";
	}

	/**
	 * Displays SignUp view
	 * 
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String displayUserRegistration(@ModelAttribute("form") UserRegistrationForm form, Model model) {
		return "UserRegistration";
	}

	/**
	 * Submits SignUp data
	 * 
	 * @param locale
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return String
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String submitUserRegistration(Locale locale, @RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid UserRegistrationForm form, BindingResult bindingResult, Model model,
			HttpSession session) throws MessagingException {

		userRegistrationValidator.validate(form, bindingResult);

		try {
			if (OP_SIGNUP.equalsIgnoreCase(operation)) {
				if (bindingResult.hasErrors()) {
					return "UserRegistration";
				}

				UserDTO dto = (UserDTO) form.getDto(session);
				dto.setRoleId(2);
				service.registerUser(dto);
			}

		} catch (DuplicateRecordException e) {
			String msg = messageSource.getMessage("msgbusiness.login", null, locale);
			model.addAttribute("error", msg);
			return "UserRegistration";
		}
		if (OP_CANCEL.equalsIgnoreCase(operation)) {
			return "redirect:register";
		}
		String msg = messageSource.getMessage("message.successfullyRegistered", null, locale);
		model.addAttribute("success", msg);
		return "Login";
	}

	/**
	 * Displays ForgetPassword View.
	 * 
	 * @param form
	 * @param session
	 * @param model
	 * @return String
	 */

	@RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
	public String forgetPassword(@ModelAttribute("form") ForgetPasswordForm form, HttpSession session, Model model) {
		System.out.println("In forgot password display");
		return "ForgetPassword";
	}

	/**
	 * Submits ForgetPassword data.
	 * 
	 * @param locale
	 * @param form
	 * @param bindingResult
	 * @param session
	 * @return String
	 * @throws MessagingException
	 * @throws DuplicateRecordException
	 */

	@RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
	public String forgetPassword(Locale locale, @RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid ForgetPasswordForm form, BindingResult bindingResult, HttpSession session,
			Model model) throws ApplicationException, DuplicateRecordException, MessagingException {
		System.out.println("operation is:" + operation);
		boolean pass = true;
		if (operation.equalsIgnoreCase(OP_GO)) {
			System.out.println("In Operation go");
			if (bindingResult.getFieldErrorCount("login") > 0) {
				pass = false;
			} else {
				System.out.println("Login on form:" + form.getLogin());
				loginId = form.getLogin();
				otp = service.forgetPassword(form.getLogin());
				if (otp == null) {
					String msg = messageSource.getMessage("message.loginNotExist", null, locale);
					model.addAttribute("error", msg);
				} else {
					System.out.println("in go otp is :" + otp);
					String msg = messageSource.getMessage("message.success.OTPsent", null, locale);
					model.addAttribute("success", msg);
				}
				/* return new ModelAndView("ForgetPassword"); */
				return "ForgetPassword";
			}
		}
		if (operation.equalsIgnoreCase(OP_SUBMIT)) {
			/*
			 * if(bindingResult.getFieldErrorCount("otp")>0){ pass = false;
			 * model.addAttribute("otpError","OTP cannot be null"); } else
			 */
			System.out.println("generated otp:" + otp);
			System.out.println("form otp =" + form.getOtp());

			if (form.getOtp().equals(null) || form.getOtp().equals("")) {
				System.out.println("otp is null");
				model.addAttribute("otpError", "OTP is required");
				pass = false;
			} else if (!(otp.equals(form.getOtp()))) {
				System.out.println("form.getOTP():" + form.getOtp());
				System.out.println("otp:" + otp);
				System.out.println("OTP do not match");
				model.addAttribute("otpError", "OTP is incorrect");
				pass = false;
			} else {
				String msg = messageSource.getMessage("message.resetYourPassword", null, locale);
				model.addAttribute("success", msg);
				return "ResetPassword";
			}

		}

		if (operation.equalsIgnoreCase(OP_SAVE)) {

			boolean flag = true;
			boolean result = false;

			forgetPasswordValidator.validate(form, bindingResult);
			if (bindingResult.hasFieldErrors("newPassword") || bindingResult.hasFieldErrors("confirmPassword")) {
				System.out.println("has errors");
				flag = false;
				return "ResetPassword";
			}

			System.out.println("Login id in operation save is:" + loginId);
			System.out.println("Password in reset pwd save operation is:" + form.getNewPassword());
			System.out.println("Confirm Password in reset pw save operation is:" + form.getConfirmPassword());

			/*
			 * if (form.getNewPassword().equals("") ||
			 * form.getNewPassword().equals(null)) { String msg =
			 * messageSource.getMessage("error.password.required", null,
			 * locale); model.addAttribute("newPassword", msg); flag = false; }
			 * if (form.getConfirmPassword().equals("") ||
			 * form.getConfirmPassword().equals(null)) { String msg =
			 * messageSource.getMessage("error.confirmPassword.required", null,
			 * locale); model.addAttribute("confirmPassword", msg); flag =
			 * false; } else if
			 * (!form.getNewPassword().equals(form.getConfirmPassword())) {
			 * String msg = messageSource.getMessage("error.confirmPassword",
			 * null, locale); model.addAttribute("confirmPassword", msg); flag =
			 * false; }
			 */
			if (flag == true) {
				result = service.resetPassword(loginId, form.getNewPassword());
			}
			System.out.println("reset from reset password is:" + result);
			if (result && flag) {
				System.out.println("inside result and flag");
				String msg = messageSource.getMessage("message.passwordHasBeenReset", null, locale);
				model.addAttribute("success", msg);
				return "redirect:/Login";
			} else {
				/* model.addAttribute("error","Password cannot be reset"); */
				return "ResetPassword";
			}
		}
		System.out.println("Pass Value:" + pass);
		if (pass == false) {
			/* return new ModelAndView("ForgetPassword"); */
			return "ForgetPassword";
		}

		/* return new ModelAndView("ResetPassword"); */
		return "ForgetPassword";

	}

	/**
	 * Displays ResetPassword View.
	 * 
	 * @param form
	 * @param session
	 * @param model
	 * @return String
	 */

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String resetPassword(@ModelAttribute("form") ResetPasswordForm form, HttpSession session, Model model) {
		System.out.println("In forgot reset password display");
		return "ResetPassword";
	}

	/**
	 * Submits ResetPassword data.
	 * 
	 * @param locale
	 * @param form
	 * @param bindingResult
	 * @param session
	 * @return String
	 */

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public String resetPassword(Locale locale, @RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid ResetPasswordForm form, BindingResult bindingResult, HttpSession session,
			Model model) {
		System.out.println("In reset Password post method");
		System.out.println("Login Id on Reset JSP:" + form.getLogin());

		return "ResetPassword";

	}
}
