package com.multi.model;

import lombok.Data;

@Data
public class CategoryVO {
	private int upCg_code; //상위 카테고리 코드
	private String upCg_name; //상위 카테고리 이름
	
	private int downCg_code; //하위 카테고리 코드
	private String downCg_name; //하위 카테고리 이름
}
