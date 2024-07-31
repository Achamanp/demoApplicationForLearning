package com.E_CommerceApplication.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDto {
	private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be longer than 100 characters")
    private String name;

    @Size(max = 500, message = "Description should not be longer than 500 characters")
    private String description;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity must be at least 0")
    private Integer quantity;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private double price;

    @NotBlank(message = "Stock is mandatory")
    private String stock;

    @NotBlank(message = "Brand is mandatory")
    @Size(max = 50, message = "Brand should not be longer than 50 characters")
    private String brand;
	private CategoryDto category;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}

}
