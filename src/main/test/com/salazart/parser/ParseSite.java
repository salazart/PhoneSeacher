package com.salazart.parser;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.salazart.eshko.Eshko;
import com.salazart.eshko.EshkoService;

public class ParseSite {
	 
	public static void main(String[] args) {
		String separateText = "Тел.";
		
		String textUrl = "http://ye.ua/ogo/%D0%BF%D0%BE%D0%B1%D1%83%D1%82-%D0%B4%D0%BE%D0%B7%D0%B2%D1%96%D0%BB%D0%BB%D1%8F.html";
		
		Eshko eshko = new Eshko(textUrl);
		List<String> messages = eshko.getMessagesByText(separateText);
		
		EshkoService eshkoService = new EshkoService();
		
		for (String message : messages) {
			String justMessage = StringUtils.substringBeforeLast(message, separateText);
			String phoneMessage = StringUtils.substringAfterLast(message, separateText);
			System.out.println(phoneMessage);
			List<String> phones = eshkoService.findPhone(phoneMessage);
			for (String phone : phones) {
				System.out.println(phone);
			}
		}
	}
}
