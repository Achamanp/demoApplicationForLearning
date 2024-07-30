package com.E_CommerceApplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.E_CommerceApplication.entity.Cart;
import com.E_CommerceApplication.entity.CartItem;
import com.E_CommerceApplication.entity.Product;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	Page<CartItem> findByProductId(Product product, Pageable p);

	Page<CartItem> findByCartId(Cart cart, Pageable p);

}
