package com.raystech.proj0.ctl;

import java.util.List;
import java.util.Locale;

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

import com.raystech.proj0.dto.StudentDTO;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.form.StudentForm;
import com.raystech.proj0.form.StudentValidator;
import com.raystech.proj0.service.CollegeServiceInt;
import com.raystech.proj0.service.StudentServiceInt;

/**
 * Contains navigation logics for Student and Student List usecases.
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Controller
@RequestMapping(value = "/ctl/Student")
public class StudentCtl extends BaseCtl {
	/**
	 * Object of StudentServiceInt
	 */
	@Autowired
	StudentServiceInt service;
	/**
	 * Object of CollegeServiceInt
	 */
	@Autowired
	CollegeServiceInt collegeService;

	/**
	 * Object of StudentServiceValidator
	 */
	@Autowired
	StudentValidator studentValidator;

	/**
	 * MessageSource Object
	 */
	@Autowired
	MessageSource messageSource;

	@Override
	public void preload(Model model) {
		List list = collegeService.search(null);
		model.addAttribute("collegeList", list);
	}

	/**
	 * Displays Student view
	 * 
	 * @param id
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") StudentForm form,
			Model model) {
		if (id != null && id > 0) {
			form.populate(service.findByPK(id));
		}
		return "Student";
	}

	/**
	 * Submits Student data
	 * 
	 * @param locale
	 * @param operation
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String submit(Locale locale, @RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid StudentForm form, BindingResult bindingResult, Model model,
			HttpSession session) {

		studentValidator.validate(form, bindingResult);
		try {
			StudentDTO dto = (StudentDTO) form.getDto(session);
			if (OP_SAVE.equalsIgnoreCase(operation)) {
				if (bindingResult.hasErrors()) {
					return "Student";
				}
				if (dto.getId() > 0) {
					service.update(dto);
					String msg = messageSource.getMessage("message.success.update", null, locale);
					model.addAttribute("success", msg);
				} else {
					service.add(dto);
					String msg = messageSource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				}
			} else if (OP_CANCEL.equalsIgnoreCase(operation)) {
				if (dto.getId() > 0) {
					return "redirect:Student/search";
				} else
					return "redirect:Student";
			}
		} catch (DuplicateRecordException e) {
			String msg = messageSource.getMessage("msgbusiness.student", null, locale);
			model.addAttribute("error", msg);
		}

		return "Student";
	}

	/**
	 * Displays StudentList view
	 * 
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") StudentForm form, Model model) {

		List list = service.search(null, form.getPageNo(), form.getPageSize());
		model.addAttribute("list", list);

		int toCheckListSize = service.search(null).size();
		int buttonNumber = toCheckListSize / form.getPageSize();
		if (toCheckListSize % form.getPageSize() != 0) {
			buttonNumber++;
		}
		model.addAttribute("buttonNumber", buttonNumber);
		return "StudentList";

	}

	/**
	 * Submits StudentList data
	 * 
	 * @param locale
	 * @param form
	 * @param operation
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Locale locale, @ModelAttribute("form") StudentForm form,
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
			return "redirect:Student";
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
			return "redirect:StudentList";
		}

		StudentDTO dto = (StudentDTO) form.getDto(session);
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

		return "StudentList";
	}

}
