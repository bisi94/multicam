package com.multi.myajax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //매개변수 받는 생성자
@NoArgsConstructor //기본생성자
public class UserVO {
	
	private int idx;
	private String name;
	private String addr;
	private String phone;
	
}
