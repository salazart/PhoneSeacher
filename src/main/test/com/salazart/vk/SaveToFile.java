package com.salazart.vk;

import java.util.List;

import com.salazart.db.services.UserService;
import com.salazart.out.models.OutUser;
import com.salazart.out.services.SaveFileService;

public class SaveToFile {
	private static final int MIN_ID = 609000;
	private static final int MAX_ID = 700000;
	private static final int COUNT_USERS = 1000;
	
	public static void main(String[] args) {
		
		UserService userService = new UserService();
		SaveFileService saveFileService = new SaveFileService();
		
		for(int i = MIN_ID; i < MAX_ID; i+=COUNT_USERS){
			System.out.println(i);
			List<OutUser> users = userService.getUsersJoinPhone(i, i+COUNT_USERS);
			saveFileService.saveUsers(users);
		}
		
	}
}
