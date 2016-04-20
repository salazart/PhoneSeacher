package com.salazart.db.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "target")
public class Target implements Serializable{

    @Column(name = "socialId")
    private long socialId;

    @Column(name = "statusTarget")
    private boolean statusTarget;
    
    public Target() {
    }
    
    public String toString(){
    	return "Target: " + String.valueOf(socialId) + "\t" + String.valueOf(statusTarget);
    }
    
    public Target(long socialId){
    	setSocialId(socialId);
    }
    
    public Target(long socialId, boolean statusTarget){
    	setSocialId(socialId);
    	setStatusTarget(statusTarget);
    }
    
    public boolean isEmpty(){
    	return socialId == 0;
    }

    public long getSocialId() {
        return socialId;
    }

    public void setSocialId(long socialId) {
        this.socialId = socialId;
    }

    public boolean getStatusTarget() {
        return statusTarget;
    }

    public void setStatusTarget(boolean statusTarget) {
        this.statusTarget = statusTarget;
    }

}
