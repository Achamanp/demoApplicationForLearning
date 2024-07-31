package com.E_CommerceApplication.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity(name = "order_det")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	@OneToMany(mappedBy = "order")
	private List<Shipping> shipping;
	private List<String> item = new ArrayList<>();
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<String> getItem() {
		return item;
	}
	public void setItem(List<String> item) {
		this.item = item;
	}
	

}
