package com.salazart.db;

import java.sql.SQLException;

import com.salazart.db.services.UserService;
import com.salazart.vk.models.VkUser;

public class UserTest {
	public static void main(String[] args) {
		UserService userService = new UserService();
		int maxId = 0;
		try {
			maxId = userService.getMaxId();
			maxId++;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		VkUser vkUser = new VkUser();
		vkUser.setSocialId(333);
		vkUser.setFirstName("hello");
		vkUser.setLastName("hello2");
		vkUser.setId(maxId);
		
		try {
			userService.insert(vkUser);
			maxId++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
