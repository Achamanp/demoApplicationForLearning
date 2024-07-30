package com.E_CommerceApplication.controller;

import com.E_CommerceApplication.payloads.ApiResponse;
import com.E_CommerceApplication.payloads.ReviewDto;
import com.E_CommerceApplication.impl.ReviewServiceImpl;
import com.E_CommerceApplication.pagination.ReviewPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @PostMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<ReviewDto> createReview(
            @RequestBody ReviewDto reviewDto,
            @PathVariable("userId") Integer userId,
            @PathVariable("productId") Integer productId) {
        ReviewDto createdReview = reviewServiceImpl.createReview(reviewDto, userId, productId);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(
            @PathVariable Integer reviewId,
            @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReview = reviewServiceImpl.updateReview(reviewId, reviewDto);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Integer reviewId) {
        ReviewDto reviewDto = reviewServiceImpl.getReviewById(reviewId);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity<ReviewPageResponse> getAllReviews(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(defaultValue = "id") String sortBy) {
        ReviewPageResponse reviewsPage = reviewServiceImpl.getAllReview(pageNumber, pageSize, sortDir, sortBy);
        return new ResponseEntity<>(reviewsPage, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Integer reviewId) {
        reviewServiceImpl.deleteReview(reviewId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Review Deleted Successfully", true),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ReviewPageResponse> getReviewsByProductId(
            @PathVariable Integer productId,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(defaultValue = "id") String sortBy) {
        ReviewPageResponse reviewsPage = reviewServiceImpl.getReviewByProductId(productId, pageNumber, pageSize, sortDir, sortBy);
        return new ResponseEntity<>(reviewsPage, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ReviewPageResponse> getReviewsByUserId(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(defaultValue = "id") String sortBy) {
        ReviewPageResponse reviewsPage = reviewServiceImpl.getReviewByUserId(id, pageNumber, pageSize, sortDir, sortBy);
        return new ResponseEntity<>(reviewsPage, HttpStatus.OK);
    }
}
