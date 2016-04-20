package com.salazart.vk.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.salazart.vk.models.ItalonOfPhone;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * This service modify phone number and returning it;
 * 
 * @author Dr
 *
 */
public class PhoneService {
	private final int MIN_LENGHT_PHONE = 9;
	private final int MAX_LENGHT_PHONE = 12;
	private final String DEFAULT_PATH_FILE_BASE = "src/main/resources/PhoneBase.xml";
	private List<ItalonOfPhone> phonesRule = getPhonesRule();

	@SuppressWarnings("unchecked")
	public List<ItalonOfPhone> getPhonesRule() {
		XStream xstream = new XStream(new StaxDriver());

		xstream.alias("phones", List.class);
		xstream.processAnnotations(ItalonOfPhone.class);

		try {
			return (List<ItalonOfPhone>) xstream.fromXML(new FileInputStream(DEFAULT_PATH_FILE_BASE));
		} catch (FileNotFoundException e) {
			return new ArrayList<ItalonOfPhone>();
		}
	}

	public boolean isPhoneModel(String phone) {
		phone = cleanPhone(phone);
		return (phone.length() >= MIN_LENGHT_PHONE && phone.length() <= MAX_LENGHT_PHONE);
	}

	public List<String> findByPrefix(String phone) {
		List<String> phones = new ArrayList<String>();

		phone = cleanPhone(phone);

		for (int i = 0; i < phonesRule.size(); i++) {
			
			String tempPhone = findByStart(phone, i);

			if (!tempPhone.isEmpty()) {
				// System.out.println(tempPhone);
				phones.add(tempPhone);
				phone = phone.substring(phonesRule.get(i).getLenght());
				i = -1;
			}
		}

		if (!phone.isEmpty()) {
			System.out.println("Невідомий формат номеру телефону: " + phone);
		}

		return phones;
	}

	private String findByStart(String phone, int i) {
		String tempPhone = "";
		if (phone.startsWith(phonesRule.get(i).getPrefix())) {
			tempPhone = cutPhone(phone, phonesRule.get(i).getLenght());
			tempPhone = addCode(tempPhone, phonesRule.get(i).getCode());
		}
		return tempPhone;
	}
	
	private String cutPhone(String phone, int lenght) {
		if (lenght <= phone.length()) {
			return phone.substring(0, lenght);
		} else {
			return "";
		}
	}

	private String addCode(String phone, String prefix) {
		if (!phone.isEmpty()) {
			return prefix + phone;
		} else {
			return "";
		}
	}

	public String cleanPhone(String phone) {
		if (phone != null) {
			phone = phone.trim();
			return phone.replaceAll("[^0-9]", "");
		} else {
			return "";
		}
	}
}
