package com.salazart.db.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "phone")
    private String phone = "";
    
    @Column(name = "socialId")
    private long socialId;
    
	public Phone() {
    }
	
	public String toString(){
		return "Phone: " + String.valueOf(socialId) + "\t" + phone;
	}
	
	public Phone(int id, String phone, long socialId){
		setId(id);
		setPhone(phone);
		setSocialId(socialId);
	}
    
    public Phone(String phone, long socialId) {
    	setPhone(phone);
    	setSocialId(socialId);
    }
	
    public Phone(String phone) {
    	setPhone(phone);
    }
    
    public long getSocialId() {
		return socialId;
	}

	public void setSocialId(long socialId) {
		this.socialId = socialId;
	}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
