package com.salesstock.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ProductType")
public class ProductType implements Serializable{
	private static final long serialVersionUID = 6886441113433240610L;

	@Id
	@Column(name="Id")
	private String id;
	
	@Column(name="Name")
	@NotNull
	private String name;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Status")
	private int status;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productCategoryId")
	ProductCategory productCategory;
	
	@OneToMany(mappedBy="productType")
	Collection<ProductSubType> productSubType;
	
	@OneToMany(mappedBy="productType")
	Collection<Products> products;
	
	public ProductType() {	}

	public ProductType(@NotNull String name, String description, @NotNull int status) {
		super();
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

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	public Collection<ProductSubType> getProductSubType() {
		return productSubType;
	}

	public void setProductSubType(Collection<ProductSubType> productSubType) {
		this.productSubType = productSubType;
	}

	
	public Collection<Products> getProducts() {
		return products;
	}

	public void setProducts(Collection<Products> products) {
		this.products = products;
	}


	@Override
	public String toString() {
		return "ProductType [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", productCategory=" + productCategory + ", productSubType=" + productSubType + "]";
	}

	
	
	
}
