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
import com.E_CommerceApplication.entity.CartItem;
import com.E_CommerceApplication.entity.Product;
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.pagination.CartItemPageResponse;
import com.E_CommerceApplication.payloads.CartItemDto;
import com.E_CommerceApplication.repository.CartItemRepository;
import com.E_CommerceApplication.repository.CartRepository;
import com.E_CommerceApplication.repository.ProductRepository;
import com.E_CommerceApplication.service.CartItemService;
@Service
public class CartItemServiceImpl implements CartItemService{
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired 
    private CartRepository cartRepository;
	@Override
	public CartItemDto createCartItem(CartItemDto cartItemDto, Integer cartId,Integer productId) {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(()-> new ResourceNotFoundException("Product", "Product id", productId));
		Cart cart = this.cartRepository.findById(cartId)
				.orElseThrow(()-> new ResourceNotFoundException("Cart", "Cart id", cartId));
		CartItem cartItem = this.modelMapper.map(cartItemDto, CartItem.class);
		cartItem.setCart(cart);
		cartItem.setProduct(product);
		CartItem saveCartItem = this.cartItemRepository.save(cartItem);
		return this.modelMapper.map(saveCartItem, CartItemDto.class);
	}

	@Override
	public CartItemDto updateCartItem(CartItemDto cartItemDto, Integer id) {
		CartItem cartItem = this.cartItemRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("CartItem","CartItem Id", id));
		cartItem.setQuantity(cartItemDto.getQuantity());
		CartItem updatedCartItem = this.cartItemRepository.save(cartItem);
		return this.modelMapper.map(updatedCartItem, CartItemDto.class);
	}

	@Override
	public CartItemDto getCartItemById(Integer id) {
		CartItem cartItem = this.cartItemRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("CartItem","CartItem Id", id));
		return this.modelMapper.map(cartItem, CartItemDto.class);
	}

	@Override
	public CartItemPageResponse getCartItem(Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageSize, pageNumber,sort);
		Page<CartItem> pages = this.cartItemRepository.findAll(p);
		List<CartItem> cartItem =  pages.getContent();
		List<CartItemDto> dtos = cartItem.stream()
				.map(cartItemDto-> this.modelMapper.map(cartItemDto, CartItemDto.class))
				.collect(Collectors.toList());
		CartItemPageResponse response = new CartItemPageResponse();
		response.setContent(dtos);
		response.setLastpage(pages.isLast());
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElement(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		return response;
	}

	@Override
	public void deleteCartItem(Integer id) {
		CartItem cartItem = this.cartItemRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("CartItem","CartItem Id", id));
		this.cartItemRepository.delete(cartItem);
		
	}

	@Override
	public CartItemPageResponse getCartItemByCart(Integer cartId, Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {
	    Cart cart = this.cartRepository.findById(cartId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cart", "Cart id", cartId));
	    Sort sort = null;
	    if (sortDir.equalsIgnoreCase("asc")) {
	        sort = Sort.by(sortBy).ascending();
	    } else {
	        sort = Sort.by(sortBy).descending();
	    }
	    Pageable p = PageRequest.of(pageNumber, pageSize, sort);
	    Page<CartItem> pages = this.cartItemRepository.findByCartId(cart, p);
	    List<CartItem> cartItems = pages.getContent();
	    List<CartItemDto> dtos = cartItems.stream()
	            .map(cartItem -> this.modelMapper.map(cartItem, CartItemDto.class))
	            .collect(Collectors.toList());
	    CartItemPageResponse response = new CartItemPageResponse();
	    response.setContent(dtos);
	    response.setLastpage(pages.isLast());
	    response.setPageNumber(pages.getNumber());
	    response.setPageSize(pages.getSize());
	    response.setTotalElement(pages.getTotalElements());
	    response.setTotalPages(pages.getTotalPages());
	    return response;
	}

	@Override
	public CartItemPageResponse getCartItemByProduct(Integer productId, Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {
	    Product product = this.productRepository.findById(productId)
	            .orElseThrow(() -> new ResourceNotFoundException("Product", "Product id", productId));
	    Sort sort = null;
	    if (sortDir.equalsIgnoreCase("asc")) {
	        sort = Sort.by(sortBy).ascending();
	    } else {
	        sort = Sort.by(sortBy).descending();
	    }
	    Pageable p = PageRequest.of(pageNumber, pageSize, sort);
	    Page<CartItem> pages = this.cartItemRepository.findByProductId(product, p);
	    List<CartItem> cartItems = pages.getContent();
	    List<CartItemDto> dtos = cartItems.stream()
	            .map(cartItem -> this.modelMapper.map(cartItem, CartItemDto.class))
	            .collect(Collectors.toList());
	    CartItemPageResponse response = new CartItemPageResponse();
	    response.setContent(dtos);
	    response.setLastpage(pages.isLast());
	    response.setPageNumber(pages.getNumber());
	    response.setPageSize(pages.getSize());
	    response.setTotalElement(pages.getTotalElements());
	    response.setTotalPages(pages.getTotalPages());
	    return response;
	}
}
