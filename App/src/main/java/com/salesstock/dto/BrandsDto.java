package com.salesstock.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class BrandsDto implements Serializable{

	String id;
	String name;
	String description;
	int status;
	List<String> ProductCategoryId;
	
	public BrandsDto() {	}

	public BrandsDto(String id, String name, String description, int status) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public List<String> getProductCategoryId() {
		return ProductCategoryId;
	}

	public void setProductCategoryId(List<String> productCategoryId) {
		ProductCategoryId = productCategoryId;
	}

	@Override
	public String toString() {
		return "BrandsDto [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", ProductCategoryId=" + ProductCategoryId + "]";
	}

	
	
}
