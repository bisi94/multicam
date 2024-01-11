package com.multi.domain;

import lombok.Data;

@Data
public class MelonVO {
	
	private String id;
	
	private String ranking;
	private String songTitle;
	private String singer;
	private String albumImage;
	
	private String crawlingTime;
	
}
