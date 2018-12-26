package com.salesstock.bean;

import java.util.Arrays;
import java.util.List;

public class ComboboxBean {

	List<Object> list;
	String[] keys;
	
	public ComboboxBean() {
	}

	public ComboboxBean(List<Object> list, String[] keys) {
		super();
		this.list = list;
		this.keys = keys;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	@Override
	public String toString() {
		return "ComboboxBean [list=" + list + ", keys=" + Arrays.toString(keys) + "]";
	}
	
	
}
