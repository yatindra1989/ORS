package com.raystech.proj0.ctl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import com.raystech.proj0.dto.MarksheetDTO;
import com.raystech.proj0.dto.StudentDTO;
import com.raystech.proj0.exception.DuplicateRecordException;
import com.raystech.proj0.form.MarksheetForm;
import com.raystech.proj0.form.MarksheetValidator;
import com.raystech.proj0.service.MarksheetServiceInt;
import com.raystech.proj0.service.StudentServiceInt;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Contains navigation logics for Marksheet and Marksheet List usecases.
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Controller
@RequestMapping(value = "/ctl/Marksheet")
public class MarksheetCtl extends BaseCtl {

	/**
	 * Object of MarksheetServiceInt
	 */
	@Autowired
	MarksheetServiceInt service;

	/**
	 * Object of StudentServiceInt
	 */
	@Autowired
	StudentServiceInt studentService;
	/**
	 * Object of MarksheetValidator
	 */
	@Autowired
	MarksheetValidator marksheetValidator;

	/**
	 * Object of MessageSource
	 */
	@Autowired
	MessageSource messageSource;

	@Override
	public void preload(Model model) {
		model.addAttribute("studentList", studentService.search(null));
	}

	/**
	 * Displays Marksheet view
	 * 
	 * @param id
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") MarksheetForm form,
			Model model) {
		if (id != null && id > 0) {
			form.populate(service.findByPK(id));
		}
		return "Marksheet";
	}

	/**
	 * Submits Marksheet data
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
			@ModelAttribute("form") @Valid MarksheetForm form, BindingResult bindingResult, Model model,
			HttpSession session) {
		marksheetValidator.validate(form, bindingResult);
		try {
			if (OP_SAVE.equalsIgnoreCase(operation)) {
				if (bindingResult.hasErrors()) {
					return "Marksheet";
				}
				MarksheetDTO dto = (MarksheetDTO) form.getDto(session);
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
				MarksheetDTO dto = (MarksheetDTO) form.getDto(session);
				if (dto.getId() > 0) {
					return "redirect:Marksheet/search";
				} else
					return "redirect:Marksheet";
			}
		} catch (DuplicateRecordException e) {
			String msg = messageSource.getMessage("msgbusiness.rollno", null, locale);
			model.addAttribute("error", msg);
		}

		return "Marksheet";
	}

	/**
	 * Displays MarksheetList view
	 * 
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") MarksheetForm form, Model model) {

		List list = service.search(null, form.getPageNo(), form.getPageSize());
		model.addAttribute("list", list);

		int toCheckListSize = service.search(null).size();
		int buttonNumber = toCheckListSize / form.getPageSize();
		if (toCheckListSize % form.getPageSize() != 0) {
			buttonNumber++;
		}
		model.addAttribute("buttonNumber", buttonNumber);
		return "MarksheetList";

	}

	/**
	 * Submits MarksheetList data
	 * 
	 * @param locale
	 * @param form
	 * @param operation
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Locale locale, @ModelAttribute("form") MarksheetForm form,
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
			return "redirect:Marksheet";
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
			return "redirect:MarksheetList";
		}

		MarksheetDTO dto = (MarksheetDTO) form.getDto(session);
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

		return "MarksheetList";
	}

	/**
	 * Get Mark Sheet View
	 * 
	 * @param form
	 * @return GetMarksheet
	 */
	@RequestMapping(value = "/getMarksheet", method = RequestMethod.GET)
	public String getMarksheet(@ModelAttribute("form") MarksheetForm form) {
		return "GetMarksheet";
	}

	/**
	 * Submit Get Mark Sheet View
	 * 
	 * @param locale
	 * @param operation
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getMarksheet", method = RequestMethod.POST)
	public String getMarksheet(Locale locale, @RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid MarksheetForm form, BindingResult bindingResult, Model model) {
		/*
		 * if(bindingResult.hasErrors()){ System.out.println("Has errors");
		 * return "GetMarksheet"; }
		 */
		if (!(form.getRollNo().isEmpty())) {
			MarksheetDTO dto = service.findByRollNo(form.getRollNo());
			if (dto != null) {
				form.populate(dto);
			} else {
				model.addAttribute("error", "Invalid Roll Number");
			}
		}
		return "GetMarksheet";

	}

	/**
	 * Displays MeritList view
	 * 
	 * @param form
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getMeritList", method = RequestMethod.GET)
	public String getMeritList(@ModelAttribute("form") MarksheetForm form, Model model) {
		model.addAttribute("meritList", service.getMeritList(form.getPageNo(), form.getPageSize()));
		return "MarksheetMeritList";
	}

	/**
	 * Submit MeritList data
	 * 
	 * @param form
	 * @param operation
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getMeritList", method = RequestMethod.POST)
	public String getMeritList(@ModelAttribute("form") MarksheetForm form,
			@RequestParam(required = false) String operation, Model model) {
		return "Welcome";
	}

	/**
	 * Send Marksheet as a PDF file on email id of student
	 * 
	 * @param modelAndView
	 * @param rollNo
	 * @param form
	 * @param model
	 * @param response
	 * @param locale
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getMarksheetOnMail", method = RequestMethod.GET)
	public String getMarksheetOnMail(ModelAndView modelAndView, @RequestParam(required = false) String rollNo,
			@ModelAttribute("form") MarksheetForm form, Model model, HttpServletResponse response, Locale locale)
			throws ServletException, IOException {

		getMarksheetAsPdf(modelAndView, rollNo, form, model, response);

		MarksheetDTO dto = service.findByRollNo(rollNo);
		StudentDTO sdto = studentService.findByPK(dto.getStudentId());
		service.sendMarksheetOnEmail(rollNo, sdto.getEmailId());
		String msg = messageSource.getMessage("message.succesMarksheetMail", null, locale);
		model.addAttribute("success", msg);
		if (dto != null) {
			form.populate(dto);
		}
		return "GetMarksheet";
	}

	/**
	 * Open the marksheet to print and save as a PDF file
	 * 
	 * @param modelAndView
	 * @param rollNo
	 * @param form
	 * @param model
	 * @param response
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getMarksheetAsPdf", method = RequestMethod.GET)
	public ModelAndView getMarksheetAsPdf(ModelAndView modelAndView, @RequestParam(required = false) String rollNo,
			@ModelAttribute("form") MarksheetForm form, Model model, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * try { JasperCompileManager .compileReportToFile(
		 * "E:/ORS_Proj0/ORS_Proj0/src/main/webapp/resources/reports/Marksheet.jrxml"
		 * ); } catch (JRException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		String printFileName = null;
		Map<String, Object> map = new HashMap<String, Object>();
		String sourceFileName = "E:/ORS_Proj0/ORS_Proj0/src/main/webapp/resources/reports/Marksheet.jasper";

		MarksheetDTO dto = service.findByRollNo(rollNo);
		List<MarksheetDTO> list = new ArrayList<MarksheetDTO>();
		list.add(dto);
		JRDataSource dataSource = new JRBeanCollectionDataSource(list, false);
		map.put("datasource", dataSource);
		modelAndView = new ModelAndView("pdfGetMarksheet", map);

		try {
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list, false);
			printFileName = JasperFillManager.fillReportToFile(sourceFileName, map, beanColDataSource);
			if (printFileName != null) {
				JasperExportManager.exportReportToPdfFile(printFileName,
						"E:/ORS_Proj0/ORS_Proj0/src/main/webapp/resources/marksheetPDF/" + rollNo + ".pdf");
			}
		} catch (JRException e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

}
