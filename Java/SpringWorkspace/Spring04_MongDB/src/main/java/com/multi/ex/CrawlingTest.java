package com.multi.ex;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.multi.common.CommonUtil;

public class CrawlingTest {

	public static void main(String[] args) {
		//CGV Movie Chart ũ�Ѹ��ϱ�
		String url="https://www.cgv.co.kr/movies/?";
		Document doc=null;
		try {
		doc=Jsoup.connect(url).get();
		//http�� ���� https�� �Ұ���
		//url�� ���������� ��ü �ҽ��� doc
		//System.out.println(doc);
		//�� �߿� �츮�� �ʿ��� �����͸� �����غ���
		//#contents > div.wrap-movie-chart > div.sect-movie-chart
		Elements elements=doc.select("div.sect-movie-chart");
		//System.out.println(elements);
		Elements rankEle=elements.select("strong.rank");
		Elements titleEle=elements.select("strong.title");
		
		
		//System.out.println(rankEle);
		Iterator<Element> movie_rank=rankEle.iterator();
		Iterator<Element> movie_title=titleEle.iterator();
		Iterator<Element> movie_reserve=elements.select("strong.percent span").iterator();
		Iterator<Element> movie_score=elements.select("span.percent").iterator();
		Iterator<Element> open_day=elements.select("span.txt-info > strong").iterator();
		Iterator<Element> movie_img=elements.select("span.thumb-image > img").iterator();
		
		int cnt=1;
		while(movie_rank.hasNext()) {
			MovieVO vo=new MovieVO();
			String rank=movie_rank.next().text();
			String title=movie_title.next().text();
			String reserve=movie_reserve.next().text();
			String score=movie_score.next().text();
			String day=open_day.next().text().substring(0,10);
			String imgUrl=movie_img.next().attr("src");//��ȭ�̹��� url���
			
			vo.setMovie_no(cnt++);
			vo.setMovie_rank(rank);
			vo.setMovie_title(title);
			vo.setMovie_reserve(reserve);
			vo.setMovie_score(score);
			vo.setMovie_score(score);
			vo.setOpen_day(day);
			vo.setMovie_image(imgUrl);
			
			vo.setRank_checkTime(CommonUtil.getDateTime("yyyy-MM-dd HH:mm:ss"));
			
			System.out.println(vo);
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
