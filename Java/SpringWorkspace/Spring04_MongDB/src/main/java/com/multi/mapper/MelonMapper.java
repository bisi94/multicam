package com.multi.mapper;

import java.util.List;

import com.multi.domain.*;

public interface MelonMapper {
	
	boolean createCollection(String collectionName);
	
	//��� ��Ʈ ����
	int insertSong(List<MelonVO> mList, String colName) throws Exception;
	
	//���� ������ ��� �뷡 ����Ʈ ��������
	List<MelonVO> getSongList(String colName) throws Exception;
	
	//������ ������ �뷡 �� ��������
	List<SumVO> getSingerSongCnt(String colName) throws Exception;
	
}
