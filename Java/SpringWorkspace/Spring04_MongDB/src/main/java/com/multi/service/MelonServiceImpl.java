package com.multi.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.multi.common.CommonUtil;
import com.multi.domain.*;
import com.multi.mapper.MelonMapper;

import lombok.extern.log4j.Log4j;

@Service("melonService")
@Log4j
public class MelonServiceImpl implements MelonService {
	
	@Inject
	private MelonMapper melonMapper;
	
	private String url="https://www.melon.com/chart/index.htm";
	
	@Override
	public int collectSongList() throws Exception {
		
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
			String songTitle=songTitleEle.select("div.ellipsis.rank01 a").text();
			String singer=songTitleEle.select("div.ellipsis.rank02>a").text();
			String imgUrl=songImg.attr("src");
			
			MelonVO vo=new MelonVO();
			vo.setRanking(rankInfo);
			vo.setSongTitle(songTitle);
			vo.setSinger(singer);
			vo.setAlbumImage(imgUrl);
			vo.setCrawlingTime(CommonUtil.getDateTime("yyyyMMddHHmmss"));
			log.info(vo);
			/////////////////////////////
			arr.add(vo);
			/////////////////////////////
		}//for---------------------
		log.info("arr.size(): "+arr.size());
		//////////////////////////////////////////
		//몽고디비에 크롤링한 데이터 삽입하기
		//컬렉션명 : Melon_yyMMdd 
		String collectionName="Melon_"+CommonUtil.getDateTime("yyMMdd");
		int cnt=this.melonMapper.insertSong(arr, collectionName);
		log.info("컬렉션명: "+collectionName);
		
		//////////////////////////////////////////
		return cnt;
	}

	@Override
	public List<MelonVO> getSongList() throws Exception {
		String collectionName="Melon_"+CommonUtil.getDateTime("yyMMdd");
		return this.melonMapper.getSongList(collectionName);
	}

	@Override
	public List<SumVO> getSingerSongCnt() throws Exception {
		String collectionName="Melon_"+CommonUtil.getDateTime("yyMMdd");
		return this.melonMapper.getSingerSongCnt(collectionName);
	}

}
