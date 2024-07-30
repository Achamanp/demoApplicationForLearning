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

import com.E_CommerceApplication.entity.Order;
import com.E_CommerceApplication.entity.User;
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.pagination.OrderPaginationResponse;
import com.E_CommerceApplication.payloads.OrderDto;
import com.E_CommerceApplication.repository.OrderRepository;
import com.E_CommerceApplication.repository.UserRepository;
import com.E_CommerceApplication.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;

	@Override
	public OrderDto updateOrder(OrderDto orderDto, Integer id) {
		Order order  = this.orderRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Order","Order Id", id));
		order.setItem(orderDto.getItem());
		order.setOrderDate(orderDto.getOrderDate());
		order.setShippingAddress(orderDto.getShippingAddress());
		order.setStatus(orderDto.getStatus());
		order.setTotalAmount(orderDto.getTotalAmount());
		Order updatedOrder = this.orderRepository.save(order);
		return this.modelMapper.map(updatedOrder,OrderDto.class);
	}

	@Override
	public OrderDto getOrderById(Integer id) {
		Order order  = this.orderRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Order","Order Id", id));
		return this.modelMapper.map(order, OrderDto.class);
	}

	@Override
	public OrderPaginationResponse getAllOrder(Integer pageNumber, Integer pageSize, String sortDir, String sortBy) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Order> pages = this.orderRepository.findAll(p);
		List<Order> order = pages.getContent();
		List<OrderDto> orderDto = order.stream()
				.map(dtos->this.modelMapper
						.map(dtos, OrderDto.class)).collect(Collectors.toList());
		OrderPaginationResponse response = new OrderPaginationResponse();
		response.setContent(orderDto);
		response.setLastpage(pages.isLast());
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		return response;
	}

	@Override
	public void deleteOrder(Integer id) {
		Order order  = this.orderRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Order","Order Id", id));
		this.orderRepository.delete(order);
		
	}

	@Override
	public OrderPaginationResponse getOrderByUserId(Integer userId, Integer pageNumber, Integer pageSize, String sortDir,
			String sortBy) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Order> pages = this.orderRepository.findByUserId(userId,p);
		List<Order> order = pages.getContent();
		List<OrderDto> orderDto = order.stream()
				.map(dtos->this.modelMapper
						.map(dtos, OrderDto.class)).collect(Collectors.toList());
		OrderPaginationResponse response = new OrderPaginationResponse();
		response.setContent(orderDto);
		response.setLastpage(pages.isLast());
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		return response;
	}

	@Override
	public OrderDto createOrder(OrderDto orderDto, Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		Order order = this.modelMapper.map(orderDto, Order.class);
	    order.setUser(user);
	    Order savedOrder = this.orderRepository.save(order);
		return this.modelMapper.map(savedOrder, OrderDto.class);
	}

}
