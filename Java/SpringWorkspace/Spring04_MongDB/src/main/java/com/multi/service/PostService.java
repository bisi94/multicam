package com.multi.service;

import java.util.List;

import com.multi.domain.PostVO;

public interface PostService {
	int insertPost(PostVO vo);
	List<PostVO> listPost();
	int deletePost(String id);
	int udatePost(PostVO vo);
	PostVO getPost(String id);
}
