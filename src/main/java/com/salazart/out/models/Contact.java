package com.salazart.out.models;

import com.salazart.vk.services.PhoneService;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("contact") 
public class Contact {
	
	@XStreamAlias("phone") 
	private String phone = "";
	
	@XStreamAlias("name")
	private String name = "";
	
	@XStreamAlias("note")
	private String note = "";
	
	public Contact() {
	}
	
	public Contact(String phone, String name, String note){
		setPhone(phone);
		setName(name);
		setNote(note);
	}
	
	/**
	 * Setting values phone and name to contact
	 * and if value number and name is mix up then they swaps 
	 * @param phone - phone
	 * @param name - name
	 */
	private void mixContact(){
		PhoneService phoneService = new PhoneService();
		if(phoneService.isPhoneModel(name)){
			if(!phoneService.isPhoneModel(phone)){
				String tempValue = name;
				name = phone;
				phone = tempValue;
			}
		}
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isEmpty(){
		mixContact();
		return (getPhone().isEmpty() || getName().isEmpty());
	}

	public String getPhone() {
		mixContact();
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		mixContact();
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
