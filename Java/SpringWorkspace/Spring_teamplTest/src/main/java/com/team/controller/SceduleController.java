package com.team.controller;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.model.DataDao;

@Controller
@PropertySource("classpath:apiKey.properties")
public class SceduleController {
	
	@Autowired
	private DataDao dataDao;
	
	private Logger log=LoggerFactory.getLogger(getClass());

	
	@RequestMapping("/tourData")
	public String data(Model model) {
		
		return "tourData";
	}
		
	@ResponseBody
	@RequestMapping("/tour")
	public ModelMap tour(@RequestParam String addr) {
		
		ModelMap map = new ModelMap();
		
		try {
			
			//관광지 검색
			//String apiURL="https://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=999999&pageNo=1&MobileOS=ETC&MobileApp=test&_type=json&serviceKey=g%2BINH4ICelRYTwvUPjujUIt%2FO1i9eSZAmhiCR9xJLT3v4P4aNkdXnRnDCkDGMKIdpXvJPsGJ9I5HTG6T2lmjkg%3D%3D";
			
			//관광정보 동기화 목록 조회
			String apiURL="https://apis.data.go.kr/B551011/KorService1/areaBasedSyncList1?numOfRows=99999&pageNo=1&MobileOS=ETC&MobileApp=2clipse&serviceKey=dQFZH0TtTMy3czHh56jjt5guPIXD5NwxITcG%2F2t1%2B%2FMf9qPjgN2Ef2C3BuB1TTGIG%2Bw01ZqfopYwOtxSqTwwQw%3D%3D&_type=json&showflag=1&listYN=Y&arrange=A";
			
			//카테고리 코드 검색
			//String apiURL="https://apis.data.go.kr/B551011/KorService1/categoryCode1?numOfRows=999999&pageNo=1&MobileOS=ETC&MobileApp=test&_type=json&serviceKey=g%2BINH4ICelRYTwvUPjujUIt%2FO1i9eSZAmhiCR9xJLT3v4P4aNkdXnRnDCkDGMKIdpXvJPsGJ9I5HTG6T2lmjkg%3D%3D";
			
			URL url=new URL(apiURL);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "text/plain");
	        int responseCode=conn.getResponseCode();
	        if(responseCode==200) {
	        	 BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	             String inputLine;
	             StringBuilder response = new StringBuilder();
	             while ((inputLine = in.readLine()) != null) {
	            	 response.append(inputLine);
	             }
	             in.close();
	             //log.info("API 응답: " + response.toString());
	             JSONObject jsonResponse = new JSONObject(response.toString());
	             JSONObject responseHeader = jsonResponse.getJSONObject("response").getJSONObject("header");
	             if ("0000".equals(responseHeader.getString("resultCode"))) {
	                 JSONObject responseBody = jsonResponse.getJSONObject("response").getJSONObject("body");
	                 JSONArray items = responseBody.getJSONObject("items").getJSONArray("item");
	                 System.out.println("data개수: "+items.length()+"개");
	                 if (items.length() > 0) {
	                	 
						/*for(int i=0;i<items.length();i++) { 
							 JSONObject firstItem = items.getJSONObject(i); 
							 String contentid = firstItem.getString("contentid");
							 String title = firstItem.getString("title");
							 String addr1 = firstItem.getString("addr1"); 
							 String mapx = firstItem.getString("mapx"); 
							 String mapy = firstItem.getString("mapy"); 
							 //log.info(title,addr1,mapx,mapy);
							 map.addAttribute("contentid"+i, contentid); 
							 map.addAttribute("title"+i, title); 
							 map.addAttribute("addr1"+i, addr1);
							 map.addAttribute("mapx"+i, mapx);
							 map.addAttribute("mapy"+i, mapy);
							 
							 dataDao.saveData(contentid, title, addr1, mapx, mapy);*/
	                	 	
	                	 for(int i=0;i<items.length();i++) { 
							 JSONObject firstItem = items.getJSONObject(i); 
							 String contentid = firstItem.getString("contentid");
							 String contenttypeid = firstItem.getString("contenttypeid");
							 String cat1 = firstItem.getString("cat1");
							 String cat2 = firstItem.getString("cat2");
							 String cat3 = firstItem.getString("cat3");
							 String title = firstItem.getString("title");
							 String tel = firstItem.getString("tel");
							 String addr1 = firstItem.getString("addr1");
							 String addr2 = firstItem.getString("addr2");
							 String firstimage = firstItem.getString("firstimage");
							 String mapx = firstItem.getString("mapx");
							 String mapy = firstItem.getString("mapy");
							 
							 map.addAttribute("contentid", contentid);
							 map.addAttribute("contenttypeid", contenttypeid);
							 map.addAttribute("cat1", cat1);
							 map.addAttribute("cat2", cat2);
							 map.addAttribute("cat3", cat3);
							 map.addAttribute("title", title);
							 map.addAttribute("tel", tel);
							 map.addAttribute("addr1", addr1);
							 map.addAttribute("addr2", addr2);
							 map.addAttribute("firstimage", firstimage);
							 map.addAttribute("mapx", mapx);
							 map.addAttribute("mapy", mapy);
							 
							 //데이터베이스에저장
							 dataDao.saveData(contentid, contenttypeid, cat1, cat2, cat3, title, tel, addr1, addr2, firstimage, mapx, mapy);
							 
						 }//for
						 System.out.println("data 저장 완료");
		             }else {
		            	System.out.println("gpt형이 했는데 안오겠냐");
		             }//if-------------------------------
	             }else {
	            	 System.out.println("안올리가없지");	 
	             }//if---------------------------------
             }//if---------------------------------------
	            		 
	        
		}catch(Exception e){
			log.error("error: {}", e.getMessage());
	        map.addAttribute("error", "Error occurred" +e.getMessage());
		}
		
		return map;
	}
}
