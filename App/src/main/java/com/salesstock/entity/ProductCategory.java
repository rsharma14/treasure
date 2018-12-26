package com.salesstock.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ProductCategory")
public class ProductCategory implements Serializable{
	private static final long serialVersionUID = 6886441113433240610L;

	@Id
	@Column(name="Id")
	String id;
	@Column(name="Name")
	@NotNull
	String name;
	@Column(name="Description")
	String description;
	@Column(name="Status")
	int status;
	
	@OneToMany(mappedBy="productCategory")
	Collection<ProductType> productType;
	
	@ManyToMany(mappedBy="productCategory")
	Collection<Brands> brands;
	
	public ProductCategory() {	}

	public ProductCategory(@NotNull String name, String description, @NotNull int status) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public ProductCategory(String id, @NotNull String name, String description, @NotNull Integer status) {
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

	public Collection<ProductType> getProductType() {
		return productType;
	}

	public void setProductType(Collection<ProductType> productType) {
		this.productType = productType;
	}

	public Collection<Brands> getBrands() {
		return brands;
	}

	public void setBrands(Collection<Brands> brands) {
		this.brands = brands;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", productType=" + productType + ", brands=" + brands + "]";
	}

	
	
	
}
