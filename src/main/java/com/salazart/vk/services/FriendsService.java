package com.salazart.vk.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.salazart.db.models.City;
import com.salazart.db.models.Phone;
import com.salazart.db.models.Target;
import com.salazart.db.services.CityService;
import com.salazart.vk.models.VkUser;

public class FriendsService {
	private VkService vkService = new VkService();
	private PhoneService phoneService = new PhoneService();
	private CityService cityService = new CityService();

	public List<VkUser> getVkUsers(long socialId) throws Exception{
		List<Long> uids = vkService.friendsById(socialId);
		List<VkUser> vkUsers = vkService.usersByUids(uids);
		vkUsers = writeCity(vkUsers);
		return vkUsers;
	}

	public List<Target> getFilteredTargets(List<VkUser> vkUsers) {
		List<Target> targetIds = new ArrayList<Target>();
		for (int i = 0; i < vkUsers.size(); i++) {
			VkUser vkUser = vkUsers.get(i);
			if("2".equals(vkUser.getCountry())){
				targetIds.add(new Target(vkUser.getSocialId()));
			};
		}
		return targetIds;
	}
	
	public List<VkUser> getFilteredUser(List<VkUser> vkUsers) {

		for (int i = 0; i < vkUsers.size(); i++) {

			List<Phone> phones = handlePhones(vkUsers.get(i));
			if (!phones.isEmpty()) {
				vkUsers.get(i).setPhones(phones);
			} else {
				vkUsers.remove(i);
				i--;
			}
		}
		return vkUsers;
	}
	
	private List<VkUser> writeCity(List<VkUser> vkUsers) throws Exception{
		for (int i = 0; i < vkUsers.size(); i++) {
			VkUser vkUser = vkUsers.get(i);
			City city = new City(vkUser.getCity(), "");
			city = cityService.getById(city);
			
			addCity(city);
			
			vkUser.setCity(city.getName());
			vkUsers.set(i, vkUser);
		}
		return vkUsers;
	}

	private void addCity(City city) throws Exception {
		if(city.getId() != 0 && city.isEmpty()){
			city.setName(vkService.nameCityById(city.getId()));
			try {
				cityService.insert(city);
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	private List<Phone> handlePhones(VkUser vkUser) {
		List<Phone> phones = new ArrayList<Phone>();

		List<String> mobilePhones = phoneService.findByPrefix(vkUser
				.getMobilePhone());

		List<String> homePhones = phoneService.findByPrefix(vkUser
				.getHomePhone());

		addPhones(phones, mobilePhones);

		addPhones(phones, homePhones);

		return phones;
	}

	private void addPhones(List<Phone> phones, List<String> mobilePhones) {
		if (!mobilePhones.isEmpty()) {
			for (String phone : mobilePhones) {
				phones.add(new Phone(phone));
			}
		}
	}
}