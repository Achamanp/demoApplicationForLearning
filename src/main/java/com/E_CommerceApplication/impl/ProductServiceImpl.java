package com.E_CommerceApplication.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.E_CommerceApplication.entity.Category;
import com.E_CommerceApplication.entity.Product;
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.pagination.ProductPageResponse;
import com.E_CommerceApplication.payloads.ProductDto;
import com.E_CommerceApplication.repository.CategoryRepository;
import com.E_CommerceApplication.repository.ProductRepository;
import com.E_CommerceApplication.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public ProductDto createProduct(ProductDto productDto, Integer categoryId) {
	    Category category = this.categoryRepository.findById(categoryId)
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
	    Product product = this.modelMapper.map(productDto, Product.class);
	    product.setCategory(category);
	    Product savedProduct = this.productRepository.save(product);
	    ProductDto savedProductDto = this.modelMapper.map(savedProduct, ProductDto.class);
	    
	    return savedProductDto;
	}


	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer id) {
		Product product = this.productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", "Product Id", id));
		product.setDescription(productDto.getDescription());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		product.setBrand(productDto.getBrand());
		product.setQuantity(productDto.getQuantity());
		Product updatedProduct = this.productRepository.save(product);
		return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Integer id) {
		Product product = this.productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", "Product Id", id));
		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductPageResponse GetAllProduct(Integer pageNumber, Integer pageSize, String sortDir, String sortBy) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Product> pages = this.productRepository.findAll(p);
		List<Product> allPages = pages.getContent();
		List<ProductDto> dtos = allPages.stream().map(productDto-> this.modelMapper.map(productDto, ProductDto.class)).collect(Collectors.toList());
		ProductPageResponse response = new ProductPageResponse();
		response.setContent(dtos);
		response.setLastpage(pages.isLast());
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		return response;
	}

	@Override
	public void deleteProduct(Integer id) {
		Product product = this.productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","Product Id", id));
		this.productRepository.delete(product);
		
	}

	@Override
	public ProductPageResponse getProductByCategoryId(Integer categoryid, Integer pageNumber, Integer pageSize,
			String sortDir, String sortBy) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Product> pages = this.productRepository.findByCategoryId(categoryid,p);
		List<Product> products = pages.getContent();
		List<ProductDto> productDto = products.stream().map(dtos->this.modelMapper.map(dtos, ProductDto.class)).collect(Collectors.toList());
		ProductPageResponse response = new ProductPageResponse();
		response.setContent(productDto);
		response.setLastpage(pages.isLast());
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		
		return response;
	}

	

}
