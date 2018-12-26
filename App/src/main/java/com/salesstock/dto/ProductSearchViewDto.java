package com.salesstock.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class ProductSearchViewDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	String ProductId;
	String ProductName;
	String ProductDesc;
	String ProductStatus;
	String PTName;
	String PTDesc;
	String PSTName;
	String PSTDesc;
	String Quantity;
	String PricePerUnit;
	String QName;
	String BrandName;
	public String getProductId() {
		return ProductId;
	}
	public void setProductId(String productId) {
		ProductId = productId;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getProductDesc() {
		return ProductDesc;
	}
	public void setProductDesc(String productDesc) {
		ProductDesc = productDesc;
	}
	public String getProductStatus() {
		return ProductStatus;
	}
	public void setProductStatus(String productStatus) {
		ProductStatus = productStatus;
	}
	public String getPTName() {
		return PTName;
	}
	public void setPTName(String pTName) {
		PTName = pTName;
	}
	public String getPTDesc() {
		return PTDesc;
	}
	public void setPTDesc(String pTDesc) {
		PTDesc = pTDesc;
	}
	public String getPSTName() {
		return PSTName;
	}
	public void setPSTName(String pSTName) {
		PSTName = pSTName;
	}
	public String getPSTDesc() {
		return PSTDesc;
	}
	public void setPSTDesc(String pSTDesc) {
		PSTDesc = pSTDesc;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getPricePerUnit() {
		return PricePerUnit;
	}
	public void setPricePerUnit(String pricePerUnit) {
		PricePerUnit = pricePerUnit;
	}
	public String getQName() {
		return QName;
	}
	public void setQName(String qName) {
		QName = qName;
	}
	public String getBrandName() {
		return BrandName;
	}
	public void setBrandName(String brandName) {
		BrandName = brandName;
	}
	
	
}
