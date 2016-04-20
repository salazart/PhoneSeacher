package com.salazart;

import java.util.List;

import com.salazart.db.services.UserService;
import com.salazart.out.models.OutUser;
import com.salazart.out.services.SaveFileService;
import com.salazart.vk.BusinesLogic;
import com.salazart.vk.services.PropService;

public class Seacher {
	private static final String VK_SAVED_ID = "vkMaxSavedId";
	private static final int COUNT_USERS = 1000;
	
	public static void main(String[] args) {
		
		if(args.length > 0){
			int vkSavedId = Integer.valueOf(PropService.getValue(VK_SAVED_ID));
			
			
			int maxId = Integer.valueOf(args[0]) + vkSavedId;
			
			UserService userService = new UserService();
			SaveFileService saveFileService = new SaveFileService();
			
			for(int i = vkSavedId; i <= maxId; i+=COUNT_USERS){
				System.out.println(i);
				List<OutUser> users = userService.getUsersJoinPhone(i, i+COUNT_USERS);
				
				System.out.println(users.size());
				saveFileService.saveUsers(users);
				PropService.setValue(VK_SAVED_ID, String.valueOf(i+COUNT_USERS));
			}
		} else {
			BusinesLogic businesLogic = new BusinesLogic();
			businesLogic.startLogic();
		}
	}
}
