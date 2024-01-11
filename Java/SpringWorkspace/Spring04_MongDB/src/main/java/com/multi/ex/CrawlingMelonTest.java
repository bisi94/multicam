package com.multi.ex;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.multi.common.CommonUtil;

public class CrawlingMelonTest {

	public static void main(String[] args) throws Exception{
		String url="https://www.melon.com/chart/index.htm";
		CrawlingMelonTest app=new CrawlingMelonTest();
		int n=app.crawlingMelon(url);
	}

	public int crawlingMelon(String url) throws Exception{
		Connection con=Jsoup.connect(url);
		Document doc=con.get();
		//System.out.println(doc);
		
		//랭킹순위, 노래제목, 가수이름, 앨범이미지 url
		Elements root=doc.select("div.d_song_list");
		//System.out.println(root);

		Elements rankEle=root.select("span.rank");
		Elements titleEle=root.select("table>tbody>tr>td:nth-child(6) div.wrap_song_info");
		Elements imgEle=root.select("div.wrap img");
		
		List<MelonVO> arr=new ArrayList<>();
		
		for(int i=0; i<rankEle.size()-1; i++) {
			Element songTitleEle=titleEle.get(i);
			Element songImg=imgEle.get(i);
			
			String rankInfo=rankEle.get(i+1).text();
			String songTitle=songTitleEle.select("div.rank01 a").text();
			String singer=songTitleEle.select("div.rank02 a").text();
			String imgUrl=songImg.attr("src");
			
			MelonVO vo=new MelonVO();
			vo.setRanking(rankInfo);
			vo.setSongTitle(songTitle);
			vo.setSinger(singer);
			vo.setAlbumImage(imgUrl);
			vo.setCrawlingTime(CommonUtil.getDateTime("yyyyMMddHHmmss"));
			System.out.println(vo);
			/////////////////////////////
			arr.add(vo);
			/////////////////////////////
		}//for---------------------
		
		return arr.size();
	}
	
}
