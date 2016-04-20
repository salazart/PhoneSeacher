package com.salazart.vk;

import java.util.List;

import com.salazart.vk.models.VkUser;
import com.salazart.vk.services.VkService;

public class FriendsById {

	public static void main(String[] args) throws Exception {

		VkService vkService = new VkService();
		List<Long> uids = vkService.friendsById(102602659);
		//166639029
		//102602659
		System.out.println("Count users uids: " + uids.size());

		List<VkUser> users = vkService.usersByUids(uids);

		System.out.println("Count users: " + users.size());
		System.out.println("uid\tfirst_name\tlast_name\tbdate\tcity\tcountry\tmobile_phone\thome_phone");

		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).toString());
		}
	}

}
