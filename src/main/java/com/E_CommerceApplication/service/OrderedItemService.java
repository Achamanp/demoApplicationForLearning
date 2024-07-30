package com.E_CommerceApplication.service;

import com.E_CommerceApplication.pagination.OrderedItemPageResponse;
import com.E_CommerceApplication.payloads.OrderedItemDto;

public interface OrderedItemService {
   public OrderedItemDto createOrderItem(OrderedItemDto orderedItemDto,Integer productId);
   public OrderedItemDto updateOrderItem(OrderedItemDto orderedItemDto, Integer id);
   public OrderedItemDto getById(Integer id);
   public OrderedItemPageResponse getAll(Integer pageNumber,Integer pageSize,String sortDir, String sortBy);
   public void deleteOrderItem(Integer id);
   public OrderedItemPageResponse getOrderedItemByProductId(Integer productId,Integer pageNumber,Integer pageSize,String sortDir, String sortBy );
}
