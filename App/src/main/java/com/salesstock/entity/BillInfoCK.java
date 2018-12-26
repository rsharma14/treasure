package com.salesstock.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BillInfoCK implements Serializable {

	@Column(name="InvoiceId")
	String invoiceId;
	
	@Column(name="ProductId")
	String productId;
	
	public BillInfoCK() {
	}

	public BillInfoCK(String invoiceId, String productId) {
		super();
		this.invoiceId = invoiceId;
		this.productId = productId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	
	
}
