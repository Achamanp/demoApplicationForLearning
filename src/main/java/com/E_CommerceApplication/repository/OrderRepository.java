package com.E_CommerceApplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.E_CommerceApplication.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	Page<Order> findByUserId(Integer userId, Pageable p);

}
