package com.E_CommerceApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.E_CommerceApplication.impl.CartServiceImpl;
import com.E_CommerceApplication.payloads.ApiResponse;
import com.E_CommerceApplication.payloads.CartDto;
import com.E_CommerceApplication.pagination.CartPageResponse;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) {
        CartDto createdCart = cartService.createCart(cartDto);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable Integer id) {
        CartDto cart = cartService.getById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> updateCart(@PathVariable Integer id, @RequestBody CartDto cartDto) {
        CartDto updatedCart = cartService.updateCart(id, cartDto);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CartPageResponse> getAllCarts(
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "id") String sortBy) {
        CartPageResponse response = cartService.getAllCart(pageSize, pageNumber, sortDir, sortBy);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Integer id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(new ApiResponse( "Cart deleted successfully", true), HttpStatus.OK);
    }
}