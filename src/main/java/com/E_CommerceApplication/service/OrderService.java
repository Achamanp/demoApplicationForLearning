package com.E_CommerceApplication.service;

import java.util.List;

import com.E_CommerceApplication.pagination.OrderPaginationResponse;
import com.E_CommerceApplication.payloads.OrderDto;

public interface OrderService {
	public OrderDto createOrder(OrderDto orderDto, Integer userId);
	public OrderDto updateOrder(OrderDto orderDto , Integer id);
	public OrderDto getOrderById(Integer id);
	public OrderPaginationResponse getAllOrder(Integer pageNumber, Integer pageSize, String sortDir,String sortBy);
	public void deleteOrder(Integer id);
	public OrderPaginationResponse getOrderByUserId(Integer userId,Integer pageNumber, Integer pageSize, String sortDir,String sortBy);

}
