package com.salesstock.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProductQuotation")
public class ProductQuotation implements Serializable{

	private static final long serialVersionUID = -182844423350937517L;
	
	@Id
	@Column(name="Id")
	String id;
	
	@Column(name="Quantity")
	int Quantity;
	@Column(name="PricePerUnit")
	double PricePerUnit;
	
	@Column(name="Discount")
	double Discount;
	
	@OneToOne(mappedBy="productQuotation")
	Products products;
	
	@OneToOne
	@JoinColumn(name="QuantityUnitId")
	QuantityUnit quantityUnit;
	
	@Column(name="VendorId")
	String VendorId;
	
	
	public ProductQuotation() {	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getQuantity() {
		return Quantity;
	}


	public void setQuantity(int quantity) {
		Quantity = quantity;
	}


	public double getPricePerUnit() {
		return PricePerUnit;
	}


	public void setPricePerUnit(double pricePerUnit) {
		PricePerUnit = pricePerUnit;
	}


	public double getDiscount() {
		return Discount;
	}


	public void setDiscount(double discount) {
		Discount = discount;
	}


	public Products getProducts() {
		return products;
	}


	public void setProducts(Products products) {
		this.products = products;
	}


	public QuantityUnit getQuantityUnit() {
		return quantityUnit;
	}


	public void setQuantityUnit(QuantityUnit quantityUnit) {
		this.quantityUnit = quantityUnit;
	}


	public String getVendorId() {
		return VendorId;
	}


	public void setVendorId(String vendorId) {
		VendorId = vendorId;
	}


	@Override
	public String toString() {
		return "ProductQuotation [id=" + id + ", Quantity=" + Quantity + ", PricePerUnit=" + PricePerUnit
				+ ", Discount=" + Discount + ", products=" + products + ", quantityUnit=" + quantityUnit + ", VendorId="
				+ VendorId + "]";
	}


}
