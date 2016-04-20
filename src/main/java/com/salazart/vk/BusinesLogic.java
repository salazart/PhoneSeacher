package com.salazart.vk;

import java.sql.SQLException;
import java.util.List;

import com.salazart.db.models.Phone;
import com.salazart.db.models.Target;
import com.salazart.db.services.PhoneService;
import com.salazart.db.services.TargetService;
import com.salazart.db.services.UserService;
import com.salazart.out.services.BackUpService;
import com.salazart.vk.models.VkUser;
import com.salazart.vk.services.FriendsService;

public class BusinesLogic {
	private TargetService targetService = new TargetService();
	private UserService userService = new UserService();
	private PhoneService phoneService = new PhoneService();
	private BackUpService backUpService = new BackUpService();
	
	private int nextIdUser;
	
	public int startLogic() {
		
		nextIdUser();
		
		FriendsService friendsService = new FriendsService();
		while (true) {
			Target target = targetService.getOneTargetByStatus();
			if(target.isEmpty()){
				return 0;
			}
			
			List<VkUser> vkUsers;
			try {
				vkUsers = friendsService.getVkUsers(target.getSocialId());
			} catch (Exception e) {
				System.err.println(e);
				sleep();
				continue;
			}
			
			vkUsers = friendsService.getFilteredUser(vkUsers);
			addUsers(vkUsers);
			
			updateTarget(target);
			
			List<Target> targets = friendsService.getFilteredTargets(vkUsers);
			addTargets(targets);
			
			backUpService.startBackUp();
		}
	}
	
	private void nextIdUser() {
		try {
			nextIdUser = userService.getMaxId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		nextIdUser++;
	}

	private void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			System.err.println(e1);
		}
	}

	private void updateTarget(Target target) {
		try {
			target.setStatusTarget(true);
			targetService.update(target);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void addTargets(List<Target> targets) {
		int count = 0;
		for (int i = 0; i < targets.size(); i++) {
			try {
				targetService.insert(targets.get(i));
				count++;
			} catch (SQLException e) {
				continue;
			}
		}
		System.out.println("Додано цілей: " + count);
	}

	private void addUsers(List<VkUser> vkUsers) {
		for (int i = 0; i < vkUsers.size(); i++) {
			VkUser vkUser = vkUsers.get(i);
			try {
				vkUser.setId(nextIdUser);
				userService.insert(vkUser);
				nextIdUser++;
				addPhones(vkUser);
				System.out.println(vkUser.toString());
			} catch (SQLException e) {
				continue;
			}
		}
	}
	
	public void addPhones(VkUser vkUser) throws SQLException {
		List<Phone> phones = vkUser.getPhones();
		if (!phones.isEmpty() && vkUser.getSocialId() != 0) {
			for (int i = 0; i < phones.size(); i++) {
				phoneService.insert(phones.get(i));
			}
		}
	}
}
