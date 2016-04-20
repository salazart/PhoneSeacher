package com.salazart;

import java.util.List;

import com.salazart.vk.models.ItalonOfPhone;
import com.salazart.vk.services.PhoneService;

public class PhoneServiceTest {
	public static void main(String[] args) {
		PhoneService phoneService = new PhoneService();
		List<ItalonOfPhone> italonFoPhones = phoneService.getPhonesRule();
		System.out.println(italonFoPhones.size());
	}
}
