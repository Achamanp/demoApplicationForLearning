package com.E_CommerceApplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.E_CommerceApplication.entity.OrderedItem;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer>{

	Page<OrderedItem> findByProductId(Integer productId, Pageable p);
}
