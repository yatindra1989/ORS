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

import com.raystech.proj0.dto.FacultyDTO;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.form.FacultyForm;
import com.raystech.proj0.form.FacultyValidator;
import com.raystech.proj0.service.CollegeServiceInt;
import com.raystech.proj0.service.FacultyServiceInt;
import com.wutka.dtd.DTDOutput;

/**
 * Contains navigation logics for Faculty and Faculty List usecases.
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Controller
@RequestMapping(value = "/ctl/Faculty")
public class FacultyCtl extends BaseCtl {

	/**
	 * Object of FacultyServiceInt
	 */
	@Autowired
	FacultyServiceInt service;
	/**
	 * Object of CollegeServiceInt
	 */
	@Autowired
	CollegeServiceInt collegeService;

	/**
	 * Object of FacultyValidator
	 */
	@Autowired
	FacultyValidator facultyValidator;

	/**
	 * MessageSource Object
	 */
	@Autowired
	MessageSource messageSource;

	@ModelAttribute
	public void preload(Model model) {
		List collegeList = collegeService.search(null);
		model.addAttribute("collegeList", collegeList);

	}

	/**
	 * Displays Faculty view
	 * 
	 * @param id
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") FacultyForm form,
			Model model) {
		if (id != null && id > 0) {
			form.populate(service.findByPK(id));
		}
		return "Faculty";
	}

	/**
	 * Submits Faculty data
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
			@ModelAttribute("form") @Valid FacultyForm form, BindingResult bindingResult, Model model,
			HttpSession session) {

		facultyValidator.validate(form, bindingResult);
		try {
			FacultyDTO dto = (FacultyDTO) form.getDto(session);
			if (OP_SAVE.equalsIgnoreCase(operation)) {
				if (bindingResult.hasErrors()) {
					return "Faculty";
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
					return "redirect:Faculty/search";
				} else {
					return "redirect:Faculty";
				}
			}
		} catch (DuplicateRecordException e) {
			String msg = messageSource.getMessage("msgbusiness.faculty", null, locale);
			model.addAttribute("error", msg);
		}

		return "Faculty";
	}

	/**
	 * Displays FacultyList view
	 * 
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") FacultyForm form, Model model) {

		List list = service.search(null, form.getPageNo(), form.getPageSize());
		model.addAttribute("list", list);

		int toCheckListSize = service.search(null).size();
		int buttonNumber = toCheckListSize / form.getPageSize();
		if (toCheckListSize % form.getPageSize() != 0) {
			buttonNumber++;
		}
		model.addAttribute("buttonNumber", buttonNumber);

		return "FacultyList";

	}

	/**
	 * Submits FacultyList data
	 * 
	 * @param locale
	 * @param form
	 * @param operation
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Locale locale, @ModelAttribute("form") FacultyForm form,
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
			return "redirect:Faculty";
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
			return "redirect:FacultyList";
		}

		FacultyDTO dto = (FacultyDTO) form.getDto(session);
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

		return "FacultyList";
	}

}
