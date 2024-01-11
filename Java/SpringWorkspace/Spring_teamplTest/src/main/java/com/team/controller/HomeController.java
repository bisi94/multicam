package com.team.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.team.service.SampleService;

@Controller
public class HomeController {

	@Inject
	private SampleService sampleService;
	
	@GetMapping("/test")
	public String test(Model m) {
		
		//int cnt=this.sampleService.getTableCount();
		//m.addAttribute("tableCnt", cnt);
		
		return "home";
	}
	
}
