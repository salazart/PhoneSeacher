package com.salazart.db.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Column(name = "socialId")
	private long socialId;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "birthDay")
	private String birthDay;

	@Column(name = "address")
	private String address;

	private List<Phone> phones;

	public User() {
		this.phones = new ArrayList<Phone>();
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(socialId + "\t");
		builder.append(firstName + "\t");
		builder.append(lastName + "\t");
		builder.append(address + "\t");
		for (int i = 0; i < phones.size(); i++) {
			builder.append(phones.get(i).getPhone() + "\t");
		}
		return builder.toString();
	}

	public List<Phone> getPhones() {
		for (int i = 0; i < phones.size(); i++) {
			phones.get(i).setSocialId(getSocialId());
		}
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public long getSocialId() {
		return socialId;
	}

	public void setSocialId(long socialId) {
		this.socialId = socialId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
