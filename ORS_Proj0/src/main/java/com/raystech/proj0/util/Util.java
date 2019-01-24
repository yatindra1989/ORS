package com.raystech.proj0.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Format Input data.
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class Util {

	/**
	 * Converts String into Date format
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = null;
		if (date != null) {
			try {
				date1 = formatter.parse(date);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return date1;

	}

	/**
	 * Convert Date into String format
	 * 
	 * @param indate
	 * @return String
	 */
	public static String getDate(Date indate) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");
		/*
		 * you can also use DateFormat reference instead of SimpleDateFormat
		 * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		 */
		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
		}
		return dateString;
	}

	/**
	 * Converts Date in String format
	 * 
	 * @param indate
	 * @return String
	 */
	public static String convertStringToDate(Date indate) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
		/*
		 * you can also use DateFormat reference instead of SimpleDateFormat
		 * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		 */
		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
		}
		return dateString;
	}

}
