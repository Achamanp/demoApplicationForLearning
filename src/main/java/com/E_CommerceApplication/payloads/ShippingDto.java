package com.E_CommerceApplication.payloads;

import java.time.LocalDateTime;

public class ShippingDto {
	private Integer id;
	private LocalDateTime shippingDate;
	private LocalDateTime deliverDate;
	private Integer trackingNumber;
	private OrderDto order;
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
	public OrderDto getOrder() {
		return order;
	}
	public void setOrder(OrderDto order) {
		this.order = order;
	}
	
	
}
