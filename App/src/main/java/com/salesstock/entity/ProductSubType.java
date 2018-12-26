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
@Table(name="ProductSubType")
public class ProductSubType implements Serializable{
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
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProductTypeId")
	ProductType productType;
	
	@OneToMany(mappedBy="productSubType")
	Collection<Products> products;
	
	public ProductSubType() {	}

	public ProductSubType(@NotNull String name, String description, @NotNull int status) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public ProductSubType(String id, @NotNull String name, String description, @NotNull Integer status) {
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

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "ProductSubType [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", productType=" + productType + ", products=" + products + "]";
	}

	
	
}
