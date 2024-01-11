package com.multi.model;

import lombok.Data;

@Data
public class ReviewVO {
	
	private int num;
	private String userid;
	private int pnum;
	
	private String title;
	private String content;
	private int score;
	private String filename;
	
	private java.sql.Date wdate;
	
}
