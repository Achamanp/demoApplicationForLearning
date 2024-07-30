package com.E_CommerceApplication.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.E_CommerceApplication.entity.Cart;
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.pagination.CartPageResponse;
import com.E_CommerceApplication.payloads.CartDto;
import com.E_CommerceApplication.repository.CartRepository;
import com.E_CommerceApplication.service.CartService;
@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CartDto createCart(CartDto cartDto) {
		Cart cart = this.modelMapper.map(cartDto, Cart.class);
		Cart saveCart = this.cartRepository.save(cart);
		return this.modelMapper.map(saveCart, CartDto.class);
	}

	@Override
	public CartDto getById(Integer id) {
		Cart cart = this.cartRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Cart", "Cart id", id));
		return this.modelMapper.map(cart, CartDto.class);
	}

	@Override
	public CartDto updateCart(Integer id, CartDto cartDto) {
		Cart cart = this.cartRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Cart", "Cart id", id));
		cart.setItems(cartDto.getItems());
		cart.setQuantity(cartDto.getQuantity());
		Cart updatedCart = this.cartRepository.save(cart);
	    return this.modelMapper.map(updatedCart,CartDto.class);
	}

	@Override
	public CartPageResponse getAllCart(Integer pageSize, Integer pageNumber, String sortDir, String sortBy) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p= PageRequest.of(pageNumber, pageSize,sort);
		Page<Cart> pages = this.cartRepository.findAll(p);
		List<Cart> cart = pages.getContent();
		List<CartDto> cartDto = cart.stream()
				.map(dtos-> this.modelMapper.map(dtos, CartDto.class)).collect(Collectors.toList());
		CartPageResponse response = new CartPageResponse();
		response.setContent(cartDto);
		response.setLastpage(pages.isLast());
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		return response;
	}

	@Override
	public void deleteCart(Integer id) {
		Cart cart = this.cartRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Cart", "Cart id", id));
		this.cartRepository.delete(cart);
		
	}

}
