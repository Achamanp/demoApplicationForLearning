package com.E_CommerceApplication.service;

import com.E_CommerceApplication.pagination.CartItemPageResponse;
import com.E_CommerceApplication.payloads.CartItemDto;

public interface CartItemService {
	public CartItemDto createCartItem(CartItemDto cartItemDto, Integer cartId,Integer productId);
	public CartItemDto updateCartItem(CartItemDto cartItemDto,Integer id);
	public CartItemDto getCartItemById(Integer id);
	public CartItemPageResponse getCartItem(Integer pageSize,Integer pageNumber, String sortBy,String sortDir);
    public void deleteCartItem(Integer id);
    public CartItemPageResponse getCartItemByCart(Integer cartId,Integer pageSize,
    		Integer pageNumber, String sortBy,String sortDir);
    public CartItemPageResponse getCartItemByProduct(Integer productId,Integer pageSize
    		,Integer pageNumber, String sortBy,String sortDir);
    
}
