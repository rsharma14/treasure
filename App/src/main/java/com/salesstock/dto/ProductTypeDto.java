package com.salesstock.dto;

public class ProductTypeDto {

	String id;
	String name;
	String description;
	String productCategoryId;
	int status;
	
	public ProductTypeDto() {	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductTypeDto [id=" + id + ", name=" + name + ", description=" + description + ", productCategoryId="
				+ productCategoryId + ", status=" + status + "]";
	}

	
	
}
