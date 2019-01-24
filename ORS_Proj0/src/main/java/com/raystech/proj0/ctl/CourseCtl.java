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

import com.raystech.proj0.dto.CourseDTO;
import com.raystech.proj0.dto.UserDTO;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.form.CourseForm;
import com.raystech.proj0.form.CourseValidator;
import com.raystech.proj0.service.CourseServiceInt;

/**
 * Contains navigation logics for Course and Course List usecases.
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Controller
@RequestMapping(value = "/ctl/Course")
public class CourseCtl extends BaseCtl {
	/**
	 * Object of CourseServiceInt
	 */
	@Autowired
	CourseServiceInt service;

	/**
	 * i18n Message source
	 */
	@Autowired
	MessageSource messageSource;
	/**
	 * Object of CourseValidator
	 */
	@Autowired
	CourseValidator courseValidator;

	@Override
	public void preload(Model model) {

	}

	/**
	 * Displays Course View
	 * 
	 * @param id
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") CourseForm form,
			Model model) {
		if (id != null && id > 0) {
			form.populate(service.findByPK(id));
		}
		return "Course";
	}

	/**
	 * Submits CourseView data
	 * 
	 * @param locale
	 * @param operation
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param session
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String submit(Locale locale, @RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid CourseForm form, BindingResult bindingResult, Model model,
			HttpSession session) {

		courseValidator.validate(form, bindingResult);
		try {
			if (OP_SAVE.equalsIgnoreCase(operation)) {
				if (bindingResult.hasErrors()) {
					return "Course";
				}
				CourseDTO dto = (CourseDTO) form.getDto(session);
				if (dto.getId() > 0) {
					service.update(dto);
					String msg = messageSource.getMessage("message.success.update", null, locale);
					model.addAttribute("success", msg);
				} else {
					long id = service.add(dto);
					String msg = messageSource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				}

			} else if (OP_CANCEL.equalsIgnoreCase(operation)) {
				CourseDTO dto = (CourseDTO) form.getDto(session);
				if (dto.getId() > 0) {
					return "redirect:Course/search";
				} else {
					return "redirect:Course";
				}
			}
		} catch (DuplicateRecordException e) {
			String msg = messageSource.getMessage("msgbusiness.course", null, locale);
			model.addAttribute("error", msg);
		}
		return "Course";
	}

	/**
	 * Displays CourseList View
	 * 
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") CourseForm form, Model model) {

		List list = service.search(null, form.getPageNo(), form.getPageSize());
		model.addAttribute("list", list);

		int toCheckListSize = service.search(null).size();
		int buttonNumber = toCheckListSize / form.getPageSize();
		if (toCheckListSize % form.getPageSize() != 0) {
			buttonNumber++;
		}
		model.addAttribute("buttonNumber", buttonNumber);
		return "CourseList";

	}

	/**
	 * Submits CourseList data
	 * 
	 * @param locale
	 * @param form
	 * @param operation
	 * @param model
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Locale locale, @ModelAttribute("form") CourseForm form,
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
			return "redirect:Course";
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
			return "redirect:CourseList";
		}

		CourseDTO dto = (CourseDTO) form.getDto(session);
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

		return "CourseList";
	}

}