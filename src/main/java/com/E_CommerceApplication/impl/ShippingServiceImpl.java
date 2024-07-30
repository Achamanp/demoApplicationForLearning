package com.E_CommerceApplication.impl;

import com.E_CommerceApplication.entity.Shipping;
import com.E_CommerceApplication.entity.Order;
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.payloads.ShippingDto;
import com.E_CommerceApplication.pagination.ShippingPageResponse;
import com.E_CommerceApplication.repository.ShippingRepository;
import com.E_CommerceApplication.repository.OrderRepository;
import com.E_CommerceApplication.service.ShippingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ShippingDto createShipping(ShippingDto shippingDto, Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        Shipping shipping = modelMapper.map(shippingDto, Shipping.class);
        shipping.setOrder(order);
        Shipping savedShipping = shippingRepository.save(shipping);
        return modelMapper.map(savedShipping, ShippingDto.class);
    }

    @Override
    public ShippingDto updateShipping(ShippingDto shippingDto, Integer id) {
        Shipping shipping = shippingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipping", "id", id));
        shipping.setDeliverDate(shippingDto.getDeliverDate());
        shipping.setShippingDate(shippingDto.getShippingDate());
        shipping.setTrackingNumber(shippingDto.getTrackingNumber());
        Shipping updatedShipping = shippingRepository.save(shipping);
        return modelMapper.map(updatedShipping, ShippingDto.class);
    }

    @Override
    public ShippingDto getShippingById(Integer id) {
        Shipping shipping = shippingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipping", "id", id));
        return modelMapper.map(shipping, ShippingDto.class);
    }

    @Override
    public void deleteShipping(Integer id) {
        Shipping shipping = shippingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipping", "id", id));
        shippingRepository.delete(shipping);
    }

    @Override
    public ShippingPageResponse getAllShippings(Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy);
        sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Shipping> page = shippingRepository.findAll(pageable);

        List<ShippingDto> dtos = page.getContent().stream()
                .map(shipping -> modelMapper.map(shipping, ShippingDto.class))
                .collect(Collectors.toList());

        ShippingPageResponse response = new ShippingPageResponse();
        response.setContent(dtos);
        response.setLastpage(page.isLast());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());

        return response;
    }

    @Override
    public ShippingPageResponse getShippingByOrderId(Integer orderId, Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {

        Sort sort = Sort.by(sortBy);
        sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Shipping> page = shippingRepository.findByOrderId(orderId, pageable);

        List<ShippingDto> dtos = page.getContent().stream()
                .map(shipping -> modelMapper.map(shipping, ShippingDto.class))
                .collect(Collectors.toList());

        ShippingPageResponse response = new ShippingPageResponse();
        response.setContent(dtos);
        response.setLastpage(page.isLast());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());

        return response;
    }
}
