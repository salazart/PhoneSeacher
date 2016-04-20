package com.salazart.spring;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.salazart.vk.models.SocialNetwork;
import com.salazart.vk.models.TypeSN;
import com.salazart.vk.models.VkUser;
import com.salazart.vk.models.response.FriendsGet;
import com.salazart.vk.services.FriendsService;
import com.salazart.vk.services.PropService;
import com.salazart.vk.utils.ParamDic;
import com.salazart.vk.utils.UrlsDic;

public class FriendsById implements CommandLineRunner{
	private static final long socialId = 10262314;
	
	private static final Logger log = LoggerFactory.getLogger(FriendsById.class);

	public static void main(String[] args) {
		SpringApplication.run(FriendsById.class);
	}

	@Override
	public void run(String... args) throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDic.VK_FRIENDS_GET)
				.queryParam(ParamDic.USER_ID, socialId);
		log.info(builder.build().encode().toUri().toString());
		
		RestTemplate restTemplate = new RestTemplate();
		FriendsGet friendsGet = restTemplate.getForObject(builder.build().encode().toUri(), FriendsGet.class);
		log.info(String.valueOf(friendsGet.getFriends().size()));
		
		FriendsService friendsService = new FriendsService();
		List<VkUser> friends = friendsService.getVkUsers(socialId);
		log.info(String.valueOf(friends.size()));
		
	}
}
