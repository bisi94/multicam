package com.multi.mapper;

import java.util.List;

import com.multi.domain.*;

public interface MelonMapper {
	
	boolean createCollection(String collectionName);
	
	//멜론 차트 저장
	int insertSong(List<MelonVO> mList, String colName) throws Exception;
	
	//오늘 수집된 멜론 노래 리스트 가져오기
	List<MelonVO> getSongList(String colName) throws Exception;
	
	//가수별 수집된 노래 수 가져오기
	List<SumVO> getSingerSongCnt(String colName) throws Exception;
	
}
