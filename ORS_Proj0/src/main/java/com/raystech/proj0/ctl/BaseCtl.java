package com.raystech.proj0.ctl;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Base Controller class of project. It contain (1) Generic operations (2)
 * Generic constants (3) Generic work flow.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class BaseCtl {

	/**
	 * Save operation key constant
	 */
	protected static final String OP_SAVE = "Save";
	/**
	 * New operation key constant
	 */
	protected static final String OP_NEW = "New";
	/**
	 * Delete operation key constant
	 */
	protected static final String OP_DELETE = "Delete";
	/**
	 * Cancel operation key constant
	 */
	protected static final String OP_CANCEL = "Cancel";
	/**
	 * Error operation key constant
	 */
	protected static final String OP_ERROR = "Error";
	/**
	 * Next operation key constant
	 */
	protected static final String OP_NEXT = "Next";
	/**
	 * Previous operation key constant
	 */
	protected static final String OP_PREVIOUS = "Previous";
	/**
	 * Logout operation key constant
	 */
	protected static final String OP_LOGOUT = "Logout";
	/**
	 * Go operation key constant
	 */
	protected static final String OP_GO = "Go";
	/**
	 * Get operation key constant
	 */
	protected static final String OP_GET = "Get";
	/**
	 * Back operation key constant
	 */
	protected static final String OP_BACK = "Back";
	/**
	 * Search operation key constant
	 */
	protected static final String OP_SEARCH = "Search";
	/**
	 * Reset operation key constant
	 */
	protected static final String OP_RESET = "Reset";

	/**
	 * Submit operation key constant
	 */
	public static final String OP_SUBMIT = "Submit";

	/**
	 * Loads preloaded data and stores in Model object.
	 * 
	 * @param model
	 */
	@ModelAttribute
	public void preload(Model model) {
	}

}
