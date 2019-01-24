package com.raystech.proj0.ctl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.raystech.proj0.dto.CollegeDTO;
import com.raystech.proj0.dto.RoleDTO;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.form.RoleForm;
import com.raystech.proj0.form.RoleValidator;
import com.raystech.proj0.service.RoleServiceInt;

/**
 * Contains navigation logics for Role and Role List usecases.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Controller
@RequestMapping(value = "/ctl/Role")
public class RoleCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(RoleCtl.class);
	/**
	 * Object of RoleServiceInt
	 */
	@Autowired
	private RoleServiceInt service;
	/**
	 * Object of RoleValidator
	 */
	@Autowired
	RoleValidator roleValidator;
	/**
	 * Object of MessageSource
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Displays Role view.
	 *
	 * @param id
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") RoleForm form, Model model,
			BindingResult br) {

		log.debug("RoleCtl doDisplay() Start");

		if (id != null && id > 0) {
			form.populate(service.findByPK(id));
		}
		return "Role";
	}

	/**
	 * Submits Role data
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
			@ModelAttribute("form") @Valid RoleForm form, BindingResult bindingResult, Model model,
			HttpSession session) {

		log.debug("RoleCtl doSubmit() Start");

		roleValidator.validate(form, bindingResult);
		try {
			RoleDTO dto = (RoleDTO) form.getDto(session);
			if (OP_SAVE.equalsIgnoreCase(operation)) {
				if (bindingResult.hasErrors()) {
					return "Role";
				}
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
				if (dto.getId() > 0) {
					return "redirect:Role/search";
				} else
					return "redirect:Role";
			}
		} catch (DuplicateRecordException e) {
			String msg = messageSource.getMessage("msgbusiness.role", null, locale);
			model.addAttribute("error", msg);
		}
		return "Role";
	}

	/**
	 * Displays Role List view.
	 *
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") RoleForm form, Model model) {

		List list = service.search(null, form.getPageNo(), form.getPageSize());
		model.addAttribute("list", list);

		int toCheckListSize = service.search(null).size();
		int buttonNumber = toCheckListSize / form.getPageSize();
		if (toCheckListSize % form.getPageSize() != 0) {
			buttonNumber++;
		}
		model.addAttribute("buttonNumber", buttonNumber);
		return "RoleList";
	}

	/**
	 * Submits Role List data.
	 *
	 * @param locale
	 * @param form
	 * @param operation
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Locale locale, @ModelAttribute("form") RoleForm form,
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
			return "redirect:Role";
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
			return "redirect:RoleList";
		}

		RoleDTO dto = (RoleDTO) form.getDto(session);
		List list = service.search(dto, pageNo, form.getPageSize());
		model.addAttribute("list", list);

		int toCheckListSize = service.search(dto).size();
		int buttonNumber = toCheckListSize / form.getPageSize();
		if (toCheckListSize % form.getPageSize() != 0) {
			buttonNumber++;
		}

		model.addAttribute("buttonNumber", buttonNumber);

		return "RoleList";
	}
}
