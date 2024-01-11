package com.multi.service;

import java.util.List;

import com.multi.domain.*;

public interface MelonService {
	//멜론노래리스트저장
	int collectSongList() throws Exception;
	//오늘 수집된 멜론노래 리스트 가져오기
	List<MelonVO> getSongList() throws Exception;
	//멜론 가수별 노래 수 가져오기
	List<SumVO> getSingerSongCnt() throws Exception;
	
}///////////////////////////
