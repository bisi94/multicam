package com.multi.myajax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //�Ű����� �޴� ������
@NoArgsConstructor //�⺻������
public class UserVO {
	
	private int idx;
	private String name;
	private String addr;
	private String phone;
	
}
