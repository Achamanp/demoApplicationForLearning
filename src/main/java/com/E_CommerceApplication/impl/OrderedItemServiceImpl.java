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

import com.E_CommerceApplication.entity.OrderedItem;
import com.E_CommerceApplication.entity.Product;
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.pagination.OrderedItemPageResponse;
import com.E_CommerceApplication.payloads.OrderedItemDto;
import com.E_CommerceApplication.repository.OrderedItemRepository;
import com.E_CommerceApplication.repository.ProductRepository;
import com.E_CommerceApplication.service.OrderedItemService;
@Service
public class OrderedItemServiceImpl implements  OrderedItemService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired 
	private ProductRepository productRepository;
	@Autowired
	private OrderedItemRepository orderedItemRepository;

	@Override
	public OrderedItemDto createOrderItem(OrderedItemDto orderedItemDto, Integer productId) {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(()-> new ResourceNotFoundException("Product","Product Id", productId));
		OrderedItem orderedItem  = this.modelMapper.map(orderedItemDto, OrderedItem.class);
		orderedItem.setProduct(product);
		OrderedItem saveItem = this.orderedItemRepository.save(orderedItem);
		
		return this.modelMapper.map(saveItem,OrderedItemDto.class);
	}

	@Override
	public OrderedItemDto updateOrderItem(OrderedItemDto orderedItemDto, Integer id) {
		OrderedItem orderedItem = this.orderedItemRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("OrderedItem"," Item Id", id));
		orderedItem.setPrice(orderedItemDto.getPrice());
		orderedItem.setQuantity(orderedItemDto.getQuantity());
		OrderedItem updatedOrderedItem = this.orderedItemRepository.save(orderedItem);
		return this.modelMapper.map(updatedOrderedItem, OrderedItemDto.class);
	}

	@Override
	public OrderedItemDto getById(Integer id) {
		OrderedItem orderedItem = this.orderedItemRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("OrderedItem"," Item Id", id));
		return this.modelMapper.map(orderedItem, OrderedItemDto.class);
	}

	@Override
	public OrderedItemPageResponse getAll(Integer pageNumber, Integer pageSize, String sortDir, String sortBy) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).ascending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<OrderedItem> pages = this.orderedItemRepository.findAll(p);
		List<OrderedItem> orderedItem = pages.getContent();
		List<OrderedItemDto> orderedItemDtos = orderedItem.stream()
				.map(dtos-> this.modelMapper.map(dtos, OrderedItemDto.class))
				.collect(Collectors.toList());
		OrderedItemPageResponse response = new OrderedItemPageResponse();
		response.setContent(orderedItemDtos);
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		response.setLastpage(pages.isLast());
		return response;
	}

	@Override
	public void deleteOrderItem(Integer id) {
		OrderedItem orderedItem = this.orderedItemRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("OrderedItem"," Item Id", id));
		this.orderedItemRepository.delete(orderedItem);
		
	}

	@Override
	public OrderedItemPageResponse getOrderedItemByProductId(Integer productId, Integer pageNumber, Integer pageSize,
			String sortDir, String sortBy) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).ascending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<OrderedItem> pages = this.orderedItemRepository.findByProductId(productId,p);
		List<OrderedItem> orderedItem = pages.getContent();
		List<OrderedItemDto> orderedItemDtos = orderedItem.stream()
				.map(dtos-> this.modelMapper.map(dtos, OrderedItemDto.class))
				.collect(Collectors.toList());
		OrderedItemPageResponse response = new OrderedItemPageResponse();
		response.setContent(orderedItemDtos);
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		response.setLastpage(pages.isLast());
		return response;
	}

}
