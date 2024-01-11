package com.multi.mapper;

import java.util.List;

import com.multi.domain.PostVO;

public interface PostMapper {
	int insertPost(PostVO vo);
	List<PostVO> listPost();
	int deletePost(String id);
	int udatePost(PostVO vo);
	PostVO getPost(String id);
}
