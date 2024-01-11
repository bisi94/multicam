package com.multi.ex;

import lombok.Data;

@Data
public class MovieVO {
	
	private int movie_no;
	private String movie_rank;
	private String movie_title;
	private String movie_reserve;
	private String movie_score;
	private String open_day;
	private String movie_image;
	
	private String rank_checkTime;//크롤링한 시간
}
