package com.E_CommerceApplication.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BrandDto {
	private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be longer than 100 characters")
    private String name;

    @Size(max = 500, message = "Description should not be longer than 500 characters")
    private String description;
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

}
