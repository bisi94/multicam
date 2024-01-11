package com.multi.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CommonUtil {
	public String addMsgLoc(Model m, String msg, String loc) {
		m.addAttribute("msg", msg);
		m.addAttribute("loc", loc);
		return "message";
	}//-------------------
	
	public String addMsgBack(Model m, String msg) {
		m.addAttribute("msg", msg);
		m.addAttribute("loc", "javascript:history.back()");
		return "message";
	}//-------------------
	
	public static String getDateTime(String fmt) {
		Date today=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(fmt);
		return sdf.format(today);
	}//--------------------
	public static String getDateTime() {
		String dateStr=getDateTime("yyyy-MM-dd");
		return dateStr;
	}//-----------------------
	
}///////////////////////////////
