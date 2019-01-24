package com.raystech.proj0.ctl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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

import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.form.ChangePasswordForm;
import com.raystech.proj0.form.ChangePasswordValidator;
import com.raystech.proj0.form.MyProfileForm;
import com.raystech.proj0.form.MyProfileValidator;
import com.raystech.proj0.form.UserForm;
import com.raystech.proj0.form.UserValidator;
import com.raystech.proj0.service.RoleServiceInt;
import com.raystech.proj0.service.UserServiceInt;
import com.raystech.proj0.util.Util;

/**
 * Contains navigation logics for User and User List usecases.
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Controller
@RequestMapping(value = "/ctl/User")
public class UserCtl extends BaseCtl {

	/**
	 * Object of UserServiceInt
	 */
	@Autowired
	UserServiceInt service;

	/**
	 * Object of RoleServiceInt
	 */
	@Autowired
	RoleServiceInt roleService;

	/**
	 * Object of UserValidator
	 */
	@Autowired
	UserValidator userValidator;

	/**
	 * Object of MyProfileValidator
	 */
	@Autowired
	MyProfileValidator myProfileValidator;

	/**
	 * Object of ChangePasswordValidator
	 */
	@Autowired
	ChangePasswordValidator changePasswordValidator;

	/**
	 * i18n Message source
	 */
	@Autowired
	private MessageSource messageSource;

	@Override
	public void preload(Model model) {

		List roleList = roleService.search(null);
		model.addAttribute("roleList", roleList);

		List userList = service.search(null);
		model.addAttribute("userListSize", userList.size());

	}

	/**
	 * Displays User view
	 * 
	 * @param id
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") UserForm form, Model model) {

		if (id != null && id > 0) {
			form.populate(service.findByPK(id));
		}
		return "User";
	}

	/**
	 * Submits User Data
	 * 
	 * @param locale
	 * @param operation
	 * @param form
	 * @param bindingResult
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String submit(Locale locale, @RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid UserForm form, BindingResult bindingResult, HttpServletRequest request,
			Model model, HttpSession session) {

		userValidator.validate(form, bindingResult);
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				if (bindingResult.hasErrors()) {
					return "User";
				}
				UserDTO dto = (UserDTO) form.getDto(session);
				if (dto.getId() > 0) {
					service.update(dto);
					String msg = messageSource.getMessage("message.success.update", null, locale);
					model.addAttribute("success", msg);
				} else {
					long id = service.add(dto);
					String msg = messageSource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				}

			} else if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
				UserDTO dto = (UserDTO) form.getDto(session);
				if (dto.getId() > 0) {
					return "redirect:User/search";
				} else {
					return "redirect:User";
				}
			}
		} catch (Exception e) {
			String msg = messageSource.getMessage("msgbusiness.login", null, locale);
			model.addAttribute("error", msg);
		}
		return "User";

	}

	/**
	 * Displays UserList view
	 * 
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") UserForm form, Model model) {

		List list = service.search(null, form.getPageNo(), form.getPageSize());
		model.addAttribute("list", list);

		int toCheckListSize = service.search(null).size();
		int buttonNumber = toCheckListSize / form.getPageSize();
		if (toCheckListSize % form.getPageSize() != 0) {
			buttonNumber++;
		}
		model.addAttribute("buttonNumber", buttonNumber);
		return "UserList";

	}

	/**
	 * Submits User list data
	 * 
	 * @param locale
	 * @param form
	 * @param operation
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Locale locale, @ModelAttribute("form") UserForm form,
			@RequestParam(required = false) String operation, Model model, HttpSession session) {

		int pageNo = form.getPageNo();
		pageNo = (pageNo < 1) ? 1 : pageNo;
		if (OP_SEARCH.equalsIgnoreCase(operation) || "Next".equalsIgnoreCase(operation)
				|| "Previous".equalsIgnoreCase(operation)) {
			if (OP_SEARCH.equalsIgnoreCase(operation)) {
				pageNo = 1;
			} else if (OP_NEXT.equalsIgnoreCase(operation)) {
				pageNo++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(operation) && pageNo > 1) {
				pageNo--;
			}
		}
		form.setPageNo(pageNo);

		if (OP_NEW.equalsIgnoreCase(operation)) {
			return "redirect:User";
		} else if (OP_DELETE.equalsIgnoreCase(operation)) {
			if (form.getIds() != null && form.getIds().length > 0) {
				pageNo = 1;
				form.setPageNo(pageNo);
				for (long id : form.getIds()) {
					service.delete(id);
				}
				String msg = messageSource.getMessage("message.delete", null, locale);
				model.addAttribute("error", msg);
			} else {
				String msg = messageSource.getMessage("message.select", null, locale);
				model.addAttribute("error", msg);
			}
		} else if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:UserList";
		}

		UserDTO dto = (UserDTO) form.getDto(session);
		List list = service.search(dto, pageNo, form.getPageSize());
		model.addAttribute("list", list);
		/*
		 * if (!OP_DELETE.equalsIgnoreCase(operation)) { if (list.size() <= 0) {
		 * String msg = messageSource.getMessage("message.noRecordFound", null,
		 * locale); model.addAttribute("error", msg);
		 * 
		 * } }
		 */

		int toCheckListSize = service.search(dto).size();
		int buttonNumber = toCheckListSize / form.getPageSize();
		if (toCheckListSize % form.getPageSize() != 0) {
			buttonNumber++;
		}
		model.addAttribute("buttonNumber", buttonNumber);
		return "UserList";
	}

	/**
	 * Displays MyProfile view
	 * 
	 * @param operation
	 * @param form
	 * @param bindingResult
	 * @param request
	 * @param session
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public String displayMyProfile(@RequestParam(required = false) String operation,
			@ModelAttribute("form") MyProfileForm form, BindingResult bindingResult, HttpServletRequest request,
			HttpSession session, Model model) {

		UserDTO dto = (UserDTO) session.getAttribute("user");
		if (dto != null) {
			form.populate(service.findByPK(dto.getId()));
		}
		return "MyProfile";
	}

	/**
	 * Submits MyProfile data
	 * 
	 * @param locale
	 * @param operation
	 * @param form
	 * @param bindingResult
	 * @param request
	 * @param session
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/myProfile", method = RequestMethod.POST)
	public String submitMyProfile(Locale locale, @RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid MyProfileForm form, BindingResult bindingResult, HttpServletRequest request,
			HttpSession session, Model model) {

		myProfileValidator.validate(form, bindingResult);
		if (OP_SAVE.equalsIgnoreCase(operation)) {
			if (bindingResult.hasErrors()) {
				return "MyProfile";
			}
			/*
			 * UserDTO dto = (UserDTO) form.getDto(session);
			 * service.update(dto); session.setAttribute("user", dto);
			 */
			UserDTO dto = service.findByPK(form.getId());

			dto.setFirstName(form.getFirstName());
			dto.setLastName(form.getLastName());
			dto.setDob(Util.getDate(form.getDob()));
			dto.setMobileNo(form.getMobileNo());
			dto.setGender(form.getGender());
			service.update(dto);
			session.setAttribute("user", dto);

			String msg = messageSource.getMessage("message.success", null, locale);
			model.addAttribute("success", msg);
		} else if (OP_CANCEL.equalsIgnoreCase(operation)) {
			return "redirect:myProfile";
		}
		return "MyProfile";
	}

	/**
	 * Displays ChangePassword view
	 * 
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String displayChangePassword(@ModelAttribute("form") ChangePasswordForm form, BindingResult bindingResult,
			Model model, HttpSession session) {
		UserDTO dto = (UserDTO) form.getDto(session);
		form.populate(dto);
		return "ChangePassword";
	}

	/**
	 * Submits ChangePassword data
	 * 
	 * @param locale
	 * @param operation
	 * @param newPassword
	 * @param oldPassword
	 * @param form
	 * @param bindingResult
	 * @param session
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String submitChangePassword(Locale locale, @RequestParam(required = false) String operation,
			String newPassword, String oldPassword, @ModelAttribute("form") @Valid ChangePasswordForm form,
			BindingResult bindingResult, HttpSession session, Model model) {

		changePasswordValidator.validate(form, bindingResult);
		if (OP_SAVE.equalsIgnoreCase(operation)) {
			if (bindingResult.hasErrors()) {
				form.populate(form.getDto(session));
				return "ChangePassword";
			}
			UserDTO dto = (UserDTO) session.getAttribute("user");
			boolean flag = service.changePassword(dto.getId(), oldPassword, newPassword);
			if (flag == true) {
				String msg = messageSource.getMessage("msgbusiness.passwordChanged", null, locale);
				model.addAttribute("success", msg);
			} else {
				String msg = messageSource.getMessage("msgbusiness.oldPwd", null, locale);
				model.addAttribute("error", msg);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(operation)) {
			return "redirect:changePassword";
		}

		return "ChangePassword";
	}

}