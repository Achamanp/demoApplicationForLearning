package com.E_CommerceApplication.payloads;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class OrderDto {
	private Integer id;

    @NotNull(message = "Order date is mandatory")
    private LocalDateTime orderDate;

    @NotBlank(message = "Status is mandatory")
    @Size(max = 50, message = "Status should not be longer than 50 characters")
    private String status;

    @NotNull(message = "Total amount is mandatory")
    @Positive(message = "Total amount must be positive")
    private double totalAmount;

    @NotBlank(message = "Shipping address is mandatory")
    @Size(max = 255, message = "Shipping address should not be longer than 255 characters")
    private String shippingAddress;
	private UserDto user;
	private List<String> item = new ArrayList<>();
	public List<String> getItem() {
		return item;
	}
	public void setItem(List<String> item) {
		this.item = item;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
}
