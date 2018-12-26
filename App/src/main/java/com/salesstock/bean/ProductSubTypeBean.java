package com.salesstock.bean;

public class ProductSubTypeBean {

	String id;
	String name;
	String description;
	String productTypeId;
	int status;
	
	public ProductSubTypeBean() {	}



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



	public String getProductTypeId() {
		return productTypeId;
	}



	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "ProductTypeBean [id=" + id + ", name=" + name + ", description=" + description + ", productTypeId="
				+ productTypeId + ", status=" + status + "]";
	}

	
	
	
}
