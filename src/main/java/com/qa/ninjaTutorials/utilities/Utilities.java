package com.qa.ninjaTutorials.utilities;

import java.util.Date;

public class Utilities {
	
	public static String generateDateTimeStamp() {
		Date date = new Date();
		String updatedDate = date.toString().replace(" ", "_").replace(":", "_");
		return "seleniumpanda" +  updatedDate + "@gmail.com";
	}
	
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGELOAD_TIME = 10;
	
}
