package com.E_CommerceApplication.service;

import com.E_CommerceApplication.payloads.ShippingDto;
import com.E_CommerceApplication.pagination.ShippingPageResponse;

public interface ShippingService {
    ShippingDto createShipping(ShippingDto shippingDto,Integer orderId);
    ShippingDto updateShipping(ShippingDto shippingDto, Integer id);
    ShippingDto getShippingById(Integer id);
    void deleteShipping(Integer id);
    ShippingPageResponse getAllShippings(Integer pageSize, Integer 
    		pageNumber, String sortBy, String sortDir);
    ShippingPageResponse getShippingByOrderId(Integer orderId,Integer pageSize, Integer 
    		pageNumber, String sortBy, String sortDir);
}
