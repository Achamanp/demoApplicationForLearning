package com.E_CommerceApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.E_CommerceApplication.entity.Brand;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

}
