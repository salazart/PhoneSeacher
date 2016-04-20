package com.salazart.eshko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.salazart.vk.services.PhoneService;

public class EshkoService{
	private PhoneService phoneService = new PhoneService();
	
	public List<String> findPhone(String phone){
		phone = phone.replaceAll("[^0-9, ]", "");
		phone = phone.replaceAll("[^0-9]", " ");
		String[] phones = phone.split(" ");
		return modifyPhones(phones);
	}
	
	private List<String> modifyPhones(String[] phones) {
		List<String> listPhones = new ArrayList<String>();
		for (int i = 0; i < phones.length; i++) {
			List<String> tempPhones = phoneService.findByPrefix(phones[i]);
			if(!tempPhones.isEmpty()){
				listPhones.addAll(tempPhones);
			}
		}
		return listPhones;
	}
}
