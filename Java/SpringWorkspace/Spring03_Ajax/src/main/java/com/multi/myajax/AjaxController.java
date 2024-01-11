package com.multi.myajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class AjaxController {
	
	@RequestMapping("/ajaxView")
	public void ajaxView() {
		// WEB-INF/views/ajaxView.jsp
	}
	
	@GetMapping(value="/ajaxText", produces = {"text/plain; charset=UTF-8"})
	@ResponseBody //��ȯ��(String)�� ������� �ƴ϶� ���䵥���͸� ��ȯ�Ѵٴ� �ǹ̷� �ٿ���
	public String ajaxResponseText(@RequestParam(defaultValue="") String phone) {
		log.info("phone: "+phone);
		
		return "10#�輭��#����#"+phone;
	}//--------------------------
	
	@GetMapping(value="/ajaxXml", produces = {"text/xml; charset=UTF-8"})
	@ResponseBody
	public Map<String, String> ajaxResponseXml(String phone){
		log.info("phone>>"+phone);
		Map<String, String> map=new HashMap<>();
		map.put("idx", "55");
		map.put("name", "�̿���");
		map.put("addr", "������ �ھ絿 1����");
		map.put("phone", phone);
		
		return map;
	}//--------------------------
	
	@GetMapping(value="/ajaxJson", produces = {"application/json; charset=UTF-8"})
	@ResponseBody
	public UserVO ajaxResponseJSON(String phone) {
		log.info("phone# "+phone);
		UserVO user=new UserVO(77,"��ö��","�������� ���ǵ���",phone);
		return user;
	}//-------------------------
	
	@PostMapping(value="/ajaxJsonList", produces = {"application/json; charset=UTF-8"})
	@ResponseBody
	public List<UserVO> ajaxList(@RequestParam String dong){
		log.info("dong="+dong);
		List<UserVO> arr=new ArrayList<>();
		arr.add(new UserVO(10, "�̿���", "���� ������"+dong+" 1����", "1111"));
		arr.add(new UserVO(20, "�Ͽ���", "���� ������"+dong+" 2����", "2111"));
		arr.add(new UserVO(30, "������", "���� ������"+dong+" 3����", "3111"));
		
		return arr;
	}
	
	
}////////////////////////////
















