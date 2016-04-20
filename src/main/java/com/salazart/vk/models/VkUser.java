package com.salazart.vk.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.salazart.db.models.Phone;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VkUser implements Serializable{
	
	private int id;
	
	@JsonProperty("uid")
	private long socialId;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("bdate")
	private String birstDay;
	
	@JsonProperty("city")
	private String city;

	@JsonProperty("country")
	private String country;
	
	@JsonProperty("mobile_phone")
	private String mobilePhone;

	@JsonProperty("home_phone")
	private String homePhone;
	
	@JsonProperty("home_town")
	private String homeTown;
	
	private List<Phone> phones = new ArrayList<Phone>();
	
	public String toString(){
	    StringBuilder builder = new StringBuilder();
	    builder.append("VkUser:\t");
	    builder.append(id + "\t");
	    builder.append(socialId + "\t");
	    builder.append(firstName + "\t");
	    builder.append(lastName + "\t");
	    builder.append(birstDay + "\t");
	    builder.append(country + "\t");
	    builder.append(city + "\t");
	    for (Phone phone : phones) {
	    	builder.append(phone.getPhone() + "\t");
		}
	    return builder.toString();
	}
	
	public String getBirthDay() {
		return birstDay;
	}

	public void getBirthDay(String birstDay) {
		this.birstDay = birstDay;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public long getSocialId() {
		return socialId;
	}

	public void setSocialId(int id) {
		this.socialId = id;
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

	public List<Phone> getPhones() {
		for (int i = 0; i < phones.size(); i++) {
			phones.get(i).setSocialId(getSocialId());
		}
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
