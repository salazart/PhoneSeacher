package com.salazart.db.models;

import java.io.Serializable;

public class City implements Serializable{
	private long id;
	private String name = "";
	
	public City(){
	}

	public City(long id, String name) {
		setId(id);
		setName(name);
	}
	
	public City(String id, String name) {
		setId(getNumberFromText(id));
		setName(name);
	}
	
	private Long getNumberFromText(String text){
		try {
			return Long.valueOf(text);
		} catch (Exception e) {
			return (long) 0;
		}
	}
	
	public String toString(){
		return "City: " + String.valueOf(id) + " " + name;
	}
	
	public boolean isEmpty(){
		return name.isEmpty();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
