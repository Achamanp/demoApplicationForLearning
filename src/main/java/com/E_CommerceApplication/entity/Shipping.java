package com.E_CommerceApplication.entity;

import java.time.LocalDateTime;

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
	private LocalDateTime shippingDate;
	private LocalDateTime deliverDate;
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
