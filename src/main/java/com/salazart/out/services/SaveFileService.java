package com.salazart.out.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.salazart.db.services.UserService;
import com.salazart.out.models.Contact;
import com.salazart.out.models.OutUser;
import com.salazart.out.models.TelBook;

public class SaveFileService {
	private UserService userService = new UserService();
	
	public void saveUsers(List<OutUser> users) {

		TelBook telBook = getTelBook(users);

		FileService fileService = new FileService();
		fileService.saveFile(telBook);
	}

	private TelBook createTelBook() {
		TelBook telBook = new TelBook();
		telBook.setLastName("ВКонтакте");
		telBook.setSection(22);
		return telBook;
	}

	private TelBook getTelBook(List<OutUser> allUsers) {
		TelBook telBook = createTelBook();
		
		List<Contact> contacts = new ArrayList<Contact>();
		for (OutUser outUser : allUsers) {
			contacts.add(converteUser(outUser));
		}
		
		telBook.setContacts(contacts);
		return telBook;
	}

	private Contact converteUser(OutUser outUser) {
		String name = modifyFirstName(outUser.getFirstName())
				+ modifyLastName(outUser.getLastName())
				+ modifyBirstBay(outUser.getBirthDay())
				+ modifyAddress(outUser.getAddress())
				+ modifyLink("vk.com/id" + outUser.getSocialId());
		name = name.trim();
		System.out.println(name + "\t" + outUser.getPhone());
		return new Contact(outUser.getPhone(), name, null);
	}

	private String modifyFirstName(String text) {
		return (text != null && !text.isEmpty()) ? text : "";
	}

	private String modifyLastName(String text) {
		return (text != null && !text.isEmpty()) ? " " + text : "";
	}

	private String modifyLink(String text) {
		return (text != null && !text.isEmpty()) ? "_" + text : "";
	}

	private String modifyBirstBay(String text) {
		if (text != null && !text.isEmpty()) {
			return "_(" + text + ")";
		} else {
			return "";
		}
	}

	private String modifyAddress(String text) {
		if (text != null && !text.isEmpty()) {
			text = StringUtils.remove(text, "Город: ");
			text = text.replaceAll("[0-9]", "");
			text = text.trim();
			return "_" + text;
		} else {
			return "";
		}
	}
}
