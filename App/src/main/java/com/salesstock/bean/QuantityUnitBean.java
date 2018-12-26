package com.salesstock.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuantityUnitBean implements Serializable{

	String id;
	
	@NotEmpty(message="Please provide input")
	@Size(max=10)
	String name;
	String description;
	int status;
	
	public QuantityUnitBean() {	}

	public QuantityUnitBean(String id, String name, String description, int status) {
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

	@Override
	public String toString() {
		return "QuantityUnitDto [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ "]";
	}
	
	
}
