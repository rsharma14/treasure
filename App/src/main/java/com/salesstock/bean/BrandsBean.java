package com.salesstock.bean;

import java.io.Serializable;
import java.util.List;

public class BrandsBean implements Serializable{

	String id;
	String name;
	String description;
	int status;
	List<String> productCategoryId;
	
	public BrandsBean() {	}

	public BrandsBean(String id, String name, String description, int status) {
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
		return productCategoryId;
	}

	public void setProductCategoryId(List<String> productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	@Override
	public String toString() {
		return "BrandsBean [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", productCategoryId=" + productCategoryId + "]";
	}

	
}
