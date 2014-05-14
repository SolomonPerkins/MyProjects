package com.sperkins.myprofile.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class will be used to do basic dates manipluation.
 * @author alexforce
 *
 */
public class DateUtils {
	
	private SimpleDateFormat dateFormat;
	private String defaultFormat = "yyyy-MM-dd";
	
	public DateUtils(){
		this.dateFormat = new SimpleDateFormat(defaultFormat);
	}

	public DateUtils(String format){
		this.dateFormat = new SimpleDateFormat(format);
	}
	
	public String convertToSimpleDate(Date date){		
		return this.dateFormat.format(date);
	}
	
	
}
