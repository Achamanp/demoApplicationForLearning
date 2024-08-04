package com.E_CommerceApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.E_CommerceApplication.impl.OrderServiceImpl;
import com.E_CommerceApplication.pagination.OrderPaginationResponse;
import com.E_CommerceApplication.payloads.ApiResponse;
import com.E_CommerceApplication.payloads.OrderDto;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @PostMapping("/user/{userId}")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto, @PathVariable Integer userId) {
        OrderDto createdOrder = orderServiceImpl.createOrder(orderDto, userId);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer id) {
        OrderDto orderDto = orderServiceImpl.getOrderById(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto, @PathVariable Integer id) {
        OrderDto updatedOrder = orderServiceImpl.updateOrder(orderDto, id);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Integer id) {
        orderServiceImpl.deleteOrder(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Order Deleted Successfully", true),HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderPaginationResponse> getAllOrders(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {
        OrderPaginationResponse response = orderServiceImpl.getAllOrder(pageNumber, pageSize, sortDir, sortBy);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<OrderPaginationResponse> getOrdersByUserId(
            @PathVariable Integer userId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {
        OrderPaginationResponse response = orderServiceImpl.getOrderByUserId(userId, pageNumber, pageSize, sortDir, sortBy);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
