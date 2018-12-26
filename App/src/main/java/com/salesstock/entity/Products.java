package com.salesstock.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Products")
public class Products implements Serializable{
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
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProductSubTypeId")
	ProductSubType productSubType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProductTypeId")
	ProductType productType;
	
	
	@ManyToMany
	@JoinTable(name="Products_Brands",
	joinColumns=  @JoinColumn(name = "ProductId"),
	inverseJoinColumns =@JoinColumn(name="BrandId")
	)
	Collection<Brands> brands;
	
	@OneToOne
	@JoinColumn(name="ProductQuotationId")
	ProductQuotation productQuotation;
	
	public Products() {	}

	public Products(@NotNull String name, String description, @NotNull int status) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ProductSubType getProductSubType() {
		return productSubType;
	}

	public void setProductSubType(ProductSubType productSubType) {
		this.productSubType = productSubType;
	}

	
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	
	public Collection<Brands> getBrands() {
		return brands;
	}

	public void setBrands(Collection<Brands> brands) {
		this.brands = brands;
	}

	
	public ProductQuotation getProductQuotation() {
		return productQuotation;
	}

	public void setProductQuotation(ProductQuotation productQuotation) {
		this.productQuotation = productQuotation;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", productSubType=" + productSubType + ", productType=" + productType + ", brands=" + brands
				+ ", productQuotation=" + productQuotation + "]";
	}

	
}
