package com.salesstock.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Brands")
public class Brands implements Serializable{

	private static final long serialVersionUID = -182844423350937517L;
	
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
	
	@ManyToMany
	@JoinTable(name="Brands_ProductCategory",
	joinColumns= @JoinColumn(name="BrandId"),
	inverseJoinColumns = @JoinColumn(name = "ProductCategoryId")
	)
	Collection<ProductCategory> productCategory;

	@ManyToMany(mappedBy="brands")
	Collection<Products> products;

	
	public Brands() {	}

	public Brands(@NotNull String name, String description, @NotNull int status) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public Brands(String id, @NotNull String name, String description, @NotNull Integer status) {
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

	public Collection<ProductCategory> getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Collection<ProductCategory> productCategory) {
		this.productCategory = productCategory;
	}

	
	public Collection<Products> getProducts() {
		return products;
	}

	public void setProducts(Collection<Products> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Brands [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", productCategory=" + productCategory + "]";
	}
	
	
	
}
