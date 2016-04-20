package com.salazart.db;

import java.sql.SQLException;

import com.salazart.db.services.UserService;
import com.salazart.vk.models.VkUser;

public class RewriteId {

	public static void main(String[] args) {
		
		UserService userService = new UserService();
		int maxId = 0;
		try {
			maxId = userService.getMaxId();
			maxId++;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		for (int i = 3000000; i < 75000000; i++) {
//			
//			System.out.print("Try update: " + i + " " + maxId);
//			
//			if(userService.updateId2(i, maxId) != 0){
//				maxId++;
//			};
//			System.out.println();
//		}
		
	}

}
