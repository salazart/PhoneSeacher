package com.salazart.vk.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("phone") 
public class ItalonOfPhone {
	@XStreamAlias("lenght")
	private int lenght;
	
	@XStreamAlias("prefix")
	private String prefix;
	
	@XStreamAlias("code")
	private String code;

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
