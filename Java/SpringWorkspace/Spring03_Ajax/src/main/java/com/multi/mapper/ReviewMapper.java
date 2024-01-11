package com.multi.mapper;

import java.util.List;

import com.multi.model.ReviewVO;

public interface ReviewMapper {

	int insertReview(ReviewVO rvo);
	
	List<ReviewVO> getReviewList(int pnum);
	
	int getTotalReviewCount(int pnum);
	
	int deleteReview(int num);
	
	int updateReview(ReviewVO rvo);
	
	ReviewVO getReview(int num);
}
