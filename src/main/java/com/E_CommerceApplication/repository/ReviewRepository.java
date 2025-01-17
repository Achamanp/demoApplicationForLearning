package com.E_CommerceApplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.E_CommerceApplication.entity.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

	Page<Review> findByProductId(Integer productId, Pageable p);
	Page<Review> findByUserId(Integer userId, Pageable p);

}
