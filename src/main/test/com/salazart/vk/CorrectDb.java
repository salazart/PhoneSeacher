package com.salazart.vk;

import java.sql.SQLException;

import com.salazart.db.services.UserService;

public class CorrectDb {

	public static void main(String[] args) throws SQLException {
		int id = 947432;
		UserService userService = new UserService();
		int nextId = userService.get(id);
		while(nextId != 0)
		
		nextId = userService.get(id);
		
		id++;
		
		userService.update(id); 
		System.out.println(nextId + " " + id);
	}

}
