package com.E_CommerceApplication.service;

import com.E_CommerceApplication.pagination.CartPageResponse;
import com.E_CommerceApplication.payloads.CartDto;

public interface CartService {
	public CartDto createCart(CartDto cartDto);
	public CartDto getById(Integer id);
	public CartDto updateCart(Integer id, CartDto cartDto);
	public CartPageResponse getAllCart(Integer pageSize, Integer pageNumber, String sortDir,String sortBy);
    public void deleteCart(Integer id);
}
