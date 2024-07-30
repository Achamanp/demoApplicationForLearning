package com.E_CommerceApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.E_CommerceApplication.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
