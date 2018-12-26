package com.salesstock.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="QuantityUnit")
public class QuantityUnit implements Serializable{

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
	
	
	public QuantityUnit() {	}

	public QuantityUnit(@NotNull String name, String description, @NotNull int status) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public QuantityUnit(String id, @NotNull String name, String description, @NotNull Integer status) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "QuantityUnit [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ "]";
	}
	
	
	
}
