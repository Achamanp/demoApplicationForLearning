package com.E_CommerceApplication.entity;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class Shipping {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	 @NotNull(message = "Shipping date is mandatory")
	private LocalDateTime shippingDate;
	 @NotNull(message = "Deliver date is mandatory")
	private LocalDateTime deliverDate;
	 @NotNull(message = "Tracking number is mandatory")
	    @Min(value = 1, message = "Tracking number must be greater than 0")
	private Integer trackingNumber;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Order order;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(LocalDateTime shippingDate) {
		this.shippingDate = shippingDate;
	}
	public LocalDateTime getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(LocalDateTime deliverDate) {
		this.deliverDate = deliverDate;
	}
	public Integer getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(Integer trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	

}
