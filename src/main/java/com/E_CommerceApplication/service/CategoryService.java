package com.E_CommerceApplication.service;


import org.springframework.stereotype.Service;

import com.E_CommerceApplication.pagination.CategoryPageResponse;
import com.E_CommerceApplication.payloads.CategoryDto;
@Service
public interface CategoryService {
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
	public CategoryDto getCategory(Integer id);
	public CategoryPageResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	public void deleteCategory(Integer id);

}
