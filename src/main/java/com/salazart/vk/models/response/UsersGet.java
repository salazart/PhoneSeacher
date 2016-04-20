package com.salazart.vk.models.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.salazart.vk.models.VkUser;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersGet extends JsonResponse{
	
	@JsonProperty("response")
	List<VkUser> vkUsers = new ArrayList<VkUser>();

	public List<VkUser> getVkUsers() {
		return vkUsers;
	}

	public void setVkUsers(List<VkUser> vkUsers) {
		this.vkUsers = vkUsers;
	}

}
