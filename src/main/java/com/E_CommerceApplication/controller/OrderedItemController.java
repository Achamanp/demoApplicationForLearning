package com.E_CommerceApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.E_CommerceApplication.impl.OrderedItemServiceImpl;
import com.E_CommerceApplication.pagination.OrderedItemPageResponse;
import com.E_CommerceApplication.payloads.ApiResponse;
import com.E_CommerceApplication.payloads.OrderedItemDto;

@RestController
@RequestMapping("/api/ordered-items")
public class OrderedItemController {

    @Autowired
    private OrderedItemServiceImpl orderedItemServiceImpl;

    @PostMapping("/product/{productId}")
    public ResponseEntity<OrderedItemDto> createOrderedItem(@RequestBody OrderedItemDto orderedItemDto, @PathVariable Integer productId) {
        OrderedItemDto createdOrderedItem = orderedItemServiceImpl.createOrderItem(orderedItemDto, productId);
        return ResponseEntity.ok(createdOrderedItem);
    }

    @PutMapping("/{orderedItemId}")
    public ResponseEntity<OrderedItemDto> updateOrderedItem(@RequestBody OrderedItemDto orderedItemDto, @PathVariable Integer orderedItemId) {
        OrderedItemDto updatedOrderedItem = orderedItemServiceImpl.updateOrderItem(orderedItemDto, orderedItemId);
        return ResponseEntity.ok(updatedOrderedItem);
    }

    @GetMapping("/{orderedItemId}")
    public ResponseEntity<OrderedItemDto> getOrderedItemById(@PathVariable Integer orderedItemId) {
        OrderedItemDto orderedItem = orderedItemServiceImpl.getById(orderedItemId);
        return ResponseEntity.ok(orderedItem);
    }

    @DeleteMapping("/{orderedItemId}")
    public ResponseEntity<ApiResponse> deleteOrderedItem(@PathVariable Integer orderedItemId) {
        orderedItemServiceImpl.deleteOrderItem(orderedItemId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Item Removed Successfully", true),HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderedItemPageResponse> getAllOrderedItems(
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
        OrderedItemPageResponse response = orderedItemServiceImpl.getAll(pageNumber, pageSize, sortDir, sortBy);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderedItemPageResponse> getOrderedItemsByProductId(
            @PathVariable Integer productId,
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
        OrderedItemPageResponse response = orderedItemServiceImpl.getOrderedItemByProductId(productId, pageNumber, pageSize, sortDir, sortBy);
        return ResponseEntity.ok(response);
    }
}
