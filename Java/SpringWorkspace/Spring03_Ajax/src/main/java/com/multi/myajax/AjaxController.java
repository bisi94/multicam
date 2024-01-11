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
	@ResponseBody //반환값(String)이 뷰네임이 아니라 응답데이터를 반환한다는 의미로 붙여줌
	public String ajaxResponseText(@RequestParam(defaultValue="") String phone) {
		log.info("phone: "+phone);
		
		return "10#김서방#서울#"+phone;
	}//--------------------------
	
	@GetMapping(value="/ajaxXml", produces = {"text/xml; charset=UTF-8"})
	@ResponseBody
	public Map<String, String> ajaxResponseXml(String phone){
		log.info("phone>>"+phone);
		Map<String, String> map=new HashMap<>();
		map.put("idx", "55");
		map.put("name", "이영희");
		map.put("addr", "광진구 자양동 1번지");
		map.put("phone", phone);
		
		return map;
	}//--------------------------
	
	@GetMapping(value="/ajaxJson", produces = {"application/json; charset=UTF-8"})
	@ResponseBody
	public UserVO ajaxResponseJSON(String phone) {
		log.info("phone# "+phone);
		UserVO user=new UserVO(77,"배철수","영등포구 여의도동",phone);
		return user;
	}//-------------------------
	
	@PostMapping(value="/ajaxJsonList", produces = {"application/json; charset=UTF-8"})
	@ResponseBody
	public List<UserVO> ajaxList(@RequestParam String dong){
		log.info("dong="+dong);
		List<UserVO> arr=new ArrayList<>();
		arr.add(new UserVO(10, "이영희", "서울 광진구"+dong+" 1번지", "1111"));
		arr.add(new UserVO(20, "일영희", "서울 광진구"+dong+" 2번지", "2111"));
		arr.add(new UserVO(30, "새영희", "서울 광진구"+dong+" 3번지", "3111"));
		
		return arr;
	}
	
	
}////////////////////////////
















