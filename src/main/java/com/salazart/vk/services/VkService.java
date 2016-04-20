package com.salazart.vk.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.salazart.vk.models.VkUser;
import com.salazart.vk.models.response.CitiesGet;
import com.salazart.vk.models.response.FriendsGet;
import com.salazart.vk.models.response.UsersGet;
import com.salazart.vk.utils.ParamDic;
import com.salazart.vk.utils.UrlsDic;

public class VkService{
	private static final String VK_ACCESS_TOKEN = "vkAccessToken";
	private static final int COUNT_UIDS = 200;
	
	private RestTemplate restTemplate;
	
	public VkService() {
		this.restTemplate = new RestTemplate();
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    messageConverters.add(new MappingJackson2HttpMessageConverter());
	    restTemplate.setMessageConverters(messageConverters);
	}
	
	public List<VkUser> usersByUids(List<Long> uids) throws Exception{
		List<VkUser> users = new ArrayList<VkUser>();
		for (int i = 0; i < uids.size(); i += COUNT_UIDS) {
			String request = requestUserByIds(uids, i);
			
			UsersGet usersGet = restTemplate.getForObject(request, UsersGet.class);
			
			if(usersGet != null && !usersGet.isErrorResponse()){
				users.addAll(usersGet.getVkUsers());
			}
		}
		return users;
	}
	
	private String requestUserByIds(List<Long> uids, int i) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDic.VK_USERS_GET)
				.queryParam(ParamDic.FIELDS_NAME, ParamDic.FIELDS_VALUES);
		
		builder.queryParam(ParamDic.UIDS, getSumUids(uids, i));
		
		String accessToken = getAccessToken();
		if(accessToken != null && !accessToken.isEmpty()){
			builder.queryParam(ParamDic.ACCESS_TOKEN, accessToken);
		}
		
		return builder.build().encode().toUri().toString();
	}

	private String getSumUids(List<Long> uids, int i) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(uids.get(i));
		int j = i + 1;
		while (j < uids.size() && j < i + COUNT_UIDS) {
			stringBuilder.append("," + uids.get(j));
			j++;
		}
		return stringBuilder.toString();
	}

	public List<Long> friendsById(long socialId) throws Exception{
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDic.VK_FRIENDS_GET)
				.queryParam(ParamDic.USER_ID, socialId);
		
		FriendsGet friendsGet = restTemplate.getForObject(builder.build().encode().toUri().toString(), FriendsGet.class);

		return (friendsGet == null || friendsGet.isErrorResponse()) ? new ArrayList<Long>() : friendsGet.getFriends();
	}
	
	public String nameCityById(long id) throws Exception{
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDic.VK_CITIES_BY_ID)
				.queryParam(ParamDic.CITY_IDS, id);
		
		CitiesGet citiesGet = restTemplate.getForObject(builder.build().encode().toUri().toString(), CitiesGet.class);
		
		if (citiesGet == null 
				|| citiesGet.isErrorResponse() 
				|| citiesGet.getCities().isEmpty()) {
			return ""; 
		} else {
			return citiesGet.getCities().get(0).getTitle();
		}
	}
	
	public String getAccessToken(){
		return PropService.getValue(VK_ACCESS_TOKEN);
	}
}
