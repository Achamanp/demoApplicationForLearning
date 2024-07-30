package com.E_CommerceApplication.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.E_CommerceApplication.entity.Product;
import com.E_CommerceApplication.entity.Review;
import com.E_CommerceApplication.entity.User;
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.pagination.ReviewPageResponse;
import com.E_CommerceApplication.payloads.ReviewDto;
import com.E_CommerceApplication.repository.ProductRepository;
import com.E_CommerceApplication.repository.ReviewRepository;
import com.E_CommerceApplication.repository.UserRepository;
import com.E_CommerceApplication.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
    private ModelMapper modelMapper;
	@Autowired
    private ReviewRepository reviewRepository;
	@Autowired
    private ProductRepository productRepository;
	@Autowired
    private UserRepository userRepository;
	@Override
	public ReviewDto createReview(ReviewDto reviewDto, Integer productId,Integer userId) {
	    User user = this.userRepository.findById(userId)
	    		.orElseThrow(()-> new ResourceNotFoundException("User","User id", userId));
	    Product product = this.productRepository.findById(userId)
	    		.orElseThrow(()-> new ResourceNotFoundException("Product", "Product id", productId));
	    Review review = this.modelMapper.map(reviewDto, Review.class);
	    review.setProduct(product);
	    review.setUser(user);
	    Review savedReview = this.reviewRepository.save(review);
		return this.modelMapper.map(savedReview, ReviewDto.class);
	}

	@Override
	public ReviewDto updateReview(Integer id, ReviewDto reviewDto) {
		Review review = this.reviewRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Review","Review Id", id));
		review.setComment(reviewDto.getComment());
		review.setRating(reviewDto.getRating());
		Review updatedReview = this.reviewRepository.save(review);
		return this.modelMapper.map(updatedReview, ReviewDto.class);
	}

	@Override
	public ReviewDto getReviewById(Integer id) {
		Review review = this.reviewRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Review","Review Id", id));
		return this.modelMapper.map(review, ReviewDto.class);
	}

	@Override
	public ReviewPageResponse getAllReview(Integer pageNumber, Integer pageSize, String sortDir, String sortBy) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageNumber,sort);
		Page<Review> pages = this.reviewRepository.findAll(p);
		List<Review> reviews = pages.getContent();
		List<ReviewDto> reviewDto = reviews.stream().map(dtos->this.modelMapper.map(dtos, ReviewDto.class)).collect(Collectors.toList());
		ReviewPageResponse response = new ReviewPageResponse();
		response.setContent(reviewDto);
		response.setLastpage(pages.isLast());
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		return response;
	}

	@Override
	public void deleteReview(Integer id) {
		Review review = this.reviewRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Review","Review Id", id));
		this.reviewRepository.delete(review);
		
	}

	@Override
	public ReviewPageResponse getReviewByProductId(Integer productId,Integer pageNumber, Integer pageSize, String sortDir, String sortBy) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageNumber,sort);
		Page<Review> pages = this.reviewRepository.findByProductId(productId,p);
		List<Review> reviews = pages.getContent();
		List<ReviewDto> reviewDto = reviews.stream().map(dtos->this.modelMapper.map(dtos, ReviewDto.class)).collect(Collectors.toList());
		ReviewPageResponse response = new ReviewPageResponse();
		response.setContent(reviewDto);
		response.setLastpage(pages.isLast());
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		return response;
	}

	@Override
	public ReviewPageResponse getReviewByUserId(Integer userId,Integer pageNumber, Integer pageSize, String sortDir, String sortBy) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageNumber,sort);
		Page<Review> pages =this.reviewRepository.findByUserId(userId,p);
		List<Review> reviews = pages.getContent();
		List<ReviewDto> reviewDto = reviews.stream().map(dtos->this.modelMapper.map(dtos, ReviewDto.class)).collect(Collectors.toList());
		ReviewPageResponse response = new ReviewPageResponse();
		response.setContent(reviewDto);
		response.setLastpage(pages.isLast());
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		return response;
	}

}
