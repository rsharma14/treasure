package com.salesstock.bean;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class ProductsBean {

	String id;
	
	@NotEmpty
	String name;
	String description;
	String productCategoryId;
	String productTypeId;
	String productSubTypeId;
	int status;
	List<String> brandId;
	String quantityUnit;
	int quantity;
	double pricePerUnit;
	
	public ProductsBean() {	}

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

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductSubTypeId() {
		return productSubTypeId;
	}

	public void setProductSubTypeId(String productSubTypeId) {
		this.productSubTypeId = productSubTypeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<String> getBrandId() {
		return brandId;
	}

	public void setBrandId(List<String> brandId) {
		this.brandId = brandId;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public String toString() {
		return "ProductsBean [id=" + id + ", name=" + name + ", description=" + description + ", productCategoryId="
				+ productCategoryId + ", productTypeId=" + productTypeId + ", productSubTypeId=" + productSubTypeId
				+ ", status=" + status + ", brandId=" + brandId + ", quantityUnit=" + quantityUnit + ", quantity="
				+ quantity + ", pricePerUnit=" + pricePerUnit + "]";
	}

		
}
