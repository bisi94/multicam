package com.multi.mongoweb;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.multi.domain.PostVO;
import com.multi.service.PostService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class PostController {
	
	@Resource(name="postService") //by name으로 주입
	private PostService pService;
	
	@GetMapping("/post")
	public String postForm() {
		
		return "post/input";
	}
	
	@PostMapping("/post")
	public String postInsert(@ModelAttribute PostVO vo) {
		log.info("vo: "+vo);
		int n=pService.insertPost(vo);
		log.info("n: "+n);
		return "redirect:list";
	}
	
	@GetMapping("/list")
	public String postList(Model m) {
		List<PostVO> postArr=pService.listPost();
		m.addAttribute("postArr",postArr);
		
		return "post/list";
	}
	
	@GetMapping("/delete/{id}")
	public String postDelete(@PathVariable("id") String id) {
		log.info(id);
		int n=pService.deletePost(id);
		
		return "redirect:../list";
	}
	
	@GetMapping("/edit/{id}")
	public String postEditForm(@PathVariable("id") String id, Model m) {
		log.info("id: "+id);
		PostVO vo=pService.getPost(id);
		
		m.addAttribute("vo", vo);
		
		return "post/edit";
	}
	
	@PostMapping("/edit")
	public String postEditEnd(PostVO vo) {
		log.info("vo: "+vo);
		int n=pService.udatePost(vo);
		
		return "redirect:list";
	}
	
}/////////////////////////////























