package com.salesstock.entity.views;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="productsearchview")
@Immutable
public class ProductSearchView {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	String ProductId;
	String ProductName;
	String ProductDesc;
	String ProductStatusId;
	String ProductStatus;
	String PCName;
	String ProductCategoryId;
	String ProductTypeId;
	String PTName;
	String PTDesc;
	String ProductSubTypeId;
	String PSTName;
	String PSTDesc;
	String Quantity;
	String PricePerUnit;
	String QName;
	String BrandId;
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
	public String getProductStatusId() {
		return ProductStatusId;
	}
	public void setProductStatusId(String productStatusId) {
		ProductStatusId = productStatusId;
	}
	public String getProductStatus() {
		return ProductStatus;
	}
	public void setProductStatus(String productStatus) {
		ProductStatus = productStatus;
	}
	public String getPCName() {
		return PCName;
	}
	public void setPCName(String pCName) {
		PCName = pCName;
	}
	public String getProductCategoryId() {
		return ProductCategoryId;
	}
	public void setProductCategoryId(String productCategoryId) {
		ProductCategoryId = productCategoryId;
	}
	public String getProductTypeId() {
		return ProductTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		ProductTypeId = productTypeId;
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
	public String getProductSubTypeId() {
		return ProductSubTypeId;
	}
	public void setProductSubTypeId(String productSubTypeId) {
		ProductSubTypeId = productSubTypeId;
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
	public String getBrandId() {
		return BrandId;
	}
	public void setBrandId(String brandId) {
		BrandId = brandId;
	}
	public String getBrandName() {
		return BrandName;
	}
	public void setBrandName(String brandName) {
		BrandName = brandName;
	}
	@Override
	public String toString() {
		return "ProductSearchView [ProductId=" + ProductId + ", ProductName=" + ProductName + ", ProductDesc="
				+ ProductDesc + ", ProductStatusId=" + ProductStatusId + ", ProductStatus=" + ProductStatus
				+ ", PCName=" + PCName + ", ProductCategoryId=" + ProductCategoryId + ", ProductTypeId=" + ProductTypeId
				+ ", PTName=" + PTName + ", PTDesc=" + PTDesc + ", ProductSubTypeId=" + ProductSubTypeId + ", PSTName="
				+ PSTName + ", PSTDesc=" + PSTDesc + ", Quantity=" + Quantity + ", PricePerUnit=" + PricePerUnit
				+ ", QName=" + QName + ", BrandId=" + BrandId + ", BrandName=" + BrandName + "]";
	}

	
	
}
