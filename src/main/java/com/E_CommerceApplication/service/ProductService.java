package com.E_CommerceApplication.service;

import java.util.List;

import com.E_CommerceApplication.pagination.ProductPageResponse;
import com.E_CommerceApplication.payloads.ProductDto;

public interface ProductService {  
	public ProductDto createProduct(ProductDto productDto, Integer CategoryId);
	public ProductDto updateProduct(ProductDto productDto , Integer id);
	public ProductDto getProductById(Integer id);
	public ProductPageResponse GetAllProduct(Integer pageNumber, Integer pageSize, String sortDir, String sortBy);
	public void deleteProduct(Integer id);
	public ProductPageResponse getProductByCategoryId(Integer categoryid, Integer pageNumber, Integer pageSize, String sortDir, String sortBy);
	
}
