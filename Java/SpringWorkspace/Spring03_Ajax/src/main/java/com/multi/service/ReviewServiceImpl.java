package com.multi.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.mapper.ReviewMapper;
import com.multi.model.ReviewVO;
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

	@Inject
	private ReviewMapper rMapper;
	
	@Override
	public int insertReview(ReviewVO rvo) {
		
		return rMapper.insertReview(rvo);
	}

	@Override
	public ReviewVO getReview(int num) {
		
		return rMapper.getReview(num);
	}
	
	@Override
	public List<ReviewVO> getReviewList(int pnum) {
		
		return rMapper.getReviewList(pnum);
	}

	@Override
	public int getTotalReviewCount(int pnum) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int deleteReview(int num) {

		return rMapper.deleteReview(num);
	}

	@Override
	public int updateReview(ReviewVO rvo) {
		return rMapper.updateReview(rvo);
	}

}
