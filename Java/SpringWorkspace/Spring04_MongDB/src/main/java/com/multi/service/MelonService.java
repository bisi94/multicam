package com.multi.service;

import java.util.List;

import com.multi.domain.*;

public interface MelonService {
	//��г뷡����Ʈ����
	int collectSongList() throws Exception;
	//���� ������ ��г뷡 ����Ʈ ��������
	List<MelonVO> getSongList() throws Exception;
	//��� ������ �뷡 �� ��������
	List<SumVO> getSingerSongCnt() throws Exception;
	
}///////////////////////////
