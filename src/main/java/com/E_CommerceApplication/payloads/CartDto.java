package com.E_CommerceApplication.payloads;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CartDto {
	private Integer id;
	 @Min(value = 1, message = "Quantity must be at least 1")
	    private Integer quantity;

	    @NotNull(message = "Items list is mandatory")
	    @Size(min = 1, message = "There must be at least one item in the list")
	    private List<String> items;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public List<String> getItems() {
		return items;
	}
	public void setItems(List<String> items) {
		this.items = items;
	}

}
