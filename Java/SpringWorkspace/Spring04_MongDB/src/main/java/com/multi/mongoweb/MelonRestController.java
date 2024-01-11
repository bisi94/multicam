package com.multi.mongoweb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.multi.domain.SumVO;
import com.multi.domain.*;
import com.multi.service.MelonService;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class MelonRestController {

	@Inject
	private MelonService melonService;
	
	@GetMapping("/melonChart")
	public ModelAndView showChart() {
		ModelAndView mv=new ModelAndView();
		Date today=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		String str=sdf.format(today);
		
		mv.addObject("today", str);//데이터 저장
		mv.setViewName("melon/chart");//뷰네임 지정
		
		return mv;
	}//--------------------------
	
	@GetMapping(value="crawling", produces = {"application/json; charset=UTF-8"})
	public ModelMap melonCrawling() throws Exception{
		int cnt=0;
		log.info("----크롤링 시작----------------");
		cnt=this.melonService.collectSongList();
		log.info("----크롤링 끝----------------");
		
		ModelMap map=new ModelMap();
		map.addAttribute("result", cnt);
		return map;
	}//---------------------------
	
	@GetMapping(value="melonList", produces= {"application/json; chatset=UTF-8"})
	public List<MelonVO> getMelonList() throws Exception{
		
		return this.melonService.getSongList();
	}
	
	@GetMapping(value="/singerSongCnt", produces= {"application/json; charset=UTF-8"})
	public List<SumVO> getSingerSongCount() throws Exception{
		
		return this.melonService.getSingerSongCnt();
	}
	
}////////////////////////////////



