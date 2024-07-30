package com.E_CommerceApplication.controller;

import com.E_CommerceApplication.payloads.ApiResponse;
import com.E_CommerceApplication.payloads.CartItemDto;
import com.E_CommerceApplication.impl.CartItemServiceImpl;
import com.E_CommerceApplication.pagination.CartItemPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemServiceImpl cartItemServiceImpl;

    @PostMapping("/cart/{cartId}/product/{productId}")
    public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto cartItemDto,
                                                      @PathVariable Integer cartId,
                                                      @PathVariable Integer productId) {
        CartItemDto createdCartItem = cartItemServiceImpl.createCartItem(cartItemDto, cartId, productId);
        return ResponseEntity.ok(createdCartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDto> updateCartItem(@RequestBody CartItemDto cartItemDto,
                                                      @PathVariable Integer id) {
        CartItemDto updatedCartItem = cartItemServiceImpl.updateCartItem(cartItemDto, id);
        return ResponseEntity.ok(updatedCartItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDto> getCartItemById(@PathVariable Integer id) {
        CartItemDto cartItemDto = cartItemServiceImpl.getCartItemById(id);
        return ResponseEntity.ok(cartItemDto);
    }

    @GetMapping("/page")
    public ResponseEntity<CartItemPageResponse> getCartItems
    (@RequestParam(value = "pageSize", defaultValue = "10",required = false) Integer pageSize,
     @RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
     @RequestParam(value = "sortBy", defaultValue = "id",required = false) String sortBy,
      @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir) {
        CartItemPageResponse response = cartItemServiceImpl
        		.getCartItem(pageSize, pageNumber, sortBy, sortDir);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Integer id) {
        cartItemServiceImpl.deleteCartItem(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("CartItem Deleted Successfully", true),HttpStatus.OK);
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<CartItemPageResponse> getCartItemsByCart(
    @PathVariable Integer cartId,
    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
    @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
    @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
    @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        CartItemPageResponse response = cartItemServiceImpl
        		.getCartItemByCart(cartId, pageSize, pageNumber, sortBy, sortDir);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<CartItemPageResponse> getCartItemsByProduct(@PathVariable Integer productId,
    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
    @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
    @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
    @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        CartItemPageResponse response = cartItemServiceImpl.getCartItemByProduct(productId, pageSize, pageNumber, sortBy, sortDir);
        return ResponseEntity.ok(response);
    }
}
