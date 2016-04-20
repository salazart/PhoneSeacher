package com.salazart.out.models;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("telbook") 
public class TelBook {

	@XStreamAlias("lastName")
	private String lastName;
	
	@XStreamAlias("firstName")
	private String firstName;
	
	//@XStreamAlias("patronymicPerson")
	@XStreamAlias("patronymic")
	private String patrName;
	
	@XStreamAlias("year")
	private int year;
	
	@XStreamAlias("adress")
	private String adress;
	
	@XStreamAlias("phones")
	private List<String> phones;
	
	@XStreamAlias("imeis")
	private List<String> imeis;
	
	@XStreamAlias("note")
	private String note;
	private int section;
	private List<Contact> contacts;

	public TelBook(){
		lastName = "";
		firstName = "";
		patrName = "";
		year = 0;
		phones = new ArrayList<String>();
		imeis = new ArrayList<String>();
		contacts  = new ArrayList<Contact>();
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}

	public void addContact(String phone, String name) {
		Contact contact = new Contact();
		contact.setPhone(phone);
		contact.setName(name);
		contacts.add(contact);
	}
	
	public boolean isEmpty(){
		if(contacts.isEmpty() 
				&& lastName.isEmpty()
				&& firstName.isEmpty()
				&& phones.isEmpty()
				&& imeis.isEmpty()){
			return true;
		} else {
			return false;
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastNamePerson) {
		this.lastName = lastNamePerson;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPatrName() {
		return patrName;
	}

	public void setPatrName(String patrName) {
		this.patrName = patrName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getAdressPerson() {
		return adress;
	}

	public void setAdressPerson(String adressPerson) {
		this.adress = adressPerson;
	}

	public List<String> getPhonesPerson() {
		return phones;
	}

	public void setPhonesPerson(List<String> phonesPerson) {
		this.phones = phonesPerson;
	}

	public List<String> getImeisPerson() {
		return imeis;
	}

	public void setImeisPerson(List<String> imeiPerson) {
		this.imeis = imeiPerson;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getNotePerson() {
		return note;
	}

	public void setNotePerson(String notePerson) {
		this.note = notePerson;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}
}
