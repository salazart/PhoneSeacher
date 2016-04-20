package com.salazart.db;

import com.salazart.db.services.PhoneService;
import com.salazart.db.services.TargetService;
import com.salazart.db.services.UserService;

public class ShowCount {

	public static void main(String[] args) {
		TargetService targetService = new TargetService();
		UserService userService = new UserService();
		PhoneService phoneService = new PhoneService();
		//targetService.printAllTargets();
		//userService.printAllUsers();
		//phoneService.printAllPhones();
		
		// List<String> ids = targetService.getAllIds();
		// List<String> ids = userService.getAllIds();
		/*
		 * try { FileOutputStream fileOutputStream = new
		 * FileOutputStream("ids.txt"); for (String id : ids) {
		 * fileOutputStream.write((id + "\r\n").getBytes()); }
		 * fileOutputStream.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

}
