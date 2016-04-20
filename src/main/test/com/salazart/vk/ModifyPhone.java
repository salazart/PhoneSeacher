package com.salazart.vk;

import java.util.List;

import com.salazart.vk.services.PhoneService;

public class ModifyPhone {

    public static void main(String[] args) {
	String examplePhones = "80976643740,80935510331 ";
	
	PhoneService phoneService = new PhoneService();
	List<String> phones = phoneService.findByPrefix(examplePhones);
	
	for (String phone : phones) {
	    System.out.println(phone);
	}
    }

}
