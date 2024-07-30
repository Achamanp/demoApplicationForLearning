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
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.pagination.CategoryPageResponse;
import com.E_CommerceApplication.payloads.CategoryDto;
import com.E_CommerceApplication.repository.CategoryRepository;
import com.E_CommerceApplication.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category savedCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category = this.categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", id));
		category.setDescription(categoryDto.getDescription());
		category.setName(categoryDto.getName());
		Category updatedCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category category = this.categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","Category id", id));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryPageResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
	    // Set default sort direction if not provided
	    Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

	    // Create pageable instance
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

	    // Fetch paginated categories from repository
	    Page<Category> categoryPage = categoryRepository.findAll(pageable);

	    // Convert Category entities to CategoryDto
	    List<CategoryDto> categoryDtos = categoryPage.getContent()
	                                                 .stream()
	                                                 .map(category -> modelMapper.map(category, CategoryDto.class))
	                                                 .collect(Collectors.toList());

	    // Create response object
	    CategoryPageResponse response = new CategoryPageResponse();
	    response.setPageSize(categoryPage.getSize());
	    response.setPageNumber(categoryPage.getNumber());
	    response.setLastpage(categoryPage.isLast());
	    response.setTotalElement(categoryPage.getTotalElements());
	    response.setTotalPages(categoryPage.getTotalPages());
	    response.setContent(categoryDtos);

	    return response;
	}


	@Override
	public void deleteCategory(Integer id) {
	     Category category = this.categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","Category id", id));
	     this.categoryRepository.delete(category);
		
	}

}
