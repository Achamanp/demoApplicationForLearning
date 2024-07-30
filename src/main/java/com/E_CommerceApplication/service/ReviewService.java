package com.E_CommerceApplication.service;


import com.E_CommerceApplication.pagination.ReviewPageResponse;
import com.E_CommerceApplication.payloads.ReviewDto;

public interface ReviewService {
	public ReviewDto createReview(ReviewDto reviewDto,Integer productId, Integer userId);
	public ReviewDto updateReview(Integer id,ReviewDto reviewDto);
	public ReviewDto getReviewById(Integer id);
	public ReviewPageResponse getAllReview(Integer pageNumber , Integer pageSize, String sortDir,String sortBy);
	public void deleteReview(Integer id);
	public ReviewPageResponse getReviewByProductId(Integer productId,Integer pageNumber, Integer pageSize, String sortDir, String sortBy);
	public ReviewPageResponse getReviewByUserId(Integer userId,Integer pageNumber, Integer pageSize, String sortDir, String sortBy);

}
