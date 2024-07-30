package com.E_CommerceApplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.E_CommerceApplication.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Page<Product> findByCategoryId(Integer categoryid, Pageable p);

}
