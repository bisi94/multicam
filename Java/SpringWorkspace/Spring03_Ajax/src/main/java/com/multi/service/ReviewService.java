package com.multi.service;

import java.util.List;

import com.multi.model.ReviewVO;

public interface ReviewService {

	int insertReview(ReviewVO rvo);

	List<ReviewVO> getReviewList(int pnum);
	int getTotalReviewCount(int pnum);

	ReviewVO getReview(int num);
	int deleteReview(int num);
	int updateReview(ReviewVO rvo);
	
}
