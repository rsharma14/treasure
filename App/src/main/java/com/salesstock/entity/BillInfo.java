package com.salesstock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BillInfo")
public class BillInfo implements Serializable{

	@EmbeddedId
	BillInfoCK billInfoCK;
	@Column
	String sessionId;
	@Column
	String productName;
	@Column
	String unit;
	@Column
	double unitPrice;
	@Column
	int quantity;
	@Column
	double total;
	@Column
	double grandTotal;
	@Column
	Date date;
	
	public BillInfo() {
	}

	public BillInfoCK getBillInfoCK() {
		return billInfoCK;
	}

	public void setBillInfoCK(BillInfoCK billInfoCK) {
		this.billInfoCK = billInfoCK;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
