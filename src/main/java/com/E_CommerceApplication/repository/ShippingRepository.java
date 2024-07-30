package com.E_CommerceApplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.E_CommerceApplication.entity.Shipping;
@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Integer>{

	Page<Shipping> findByOrderId(Integer orderId, Pageable pageable);

}
