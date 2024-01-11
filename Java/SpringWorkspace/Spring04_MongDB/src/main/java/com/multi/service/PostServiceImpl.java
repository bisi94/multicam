package com.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.domain.PostVO;
import com.multi.mapper.PostMapper;
@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired //by type inject
	private PostMapper pMapper;
	
	@Override
	public int insertPost(PostVO vo) {
		
		return pMapper.insertPost(vo);
	}

	@Override
	public List<PostVO> listPost() {
		
		
		return pMapper.listPost();
	}

	@Override
	public int deletePost(String id) {
		
		return pMapper.deletePost(id);
	}

	@Override
	public int udatePost(PostVO vo) {
		
		return pMapper.udatePost(vo);
	}

	@Override
	public PostVO getPost(String id) {
		return pMapper.getPost(id);
	}

}
