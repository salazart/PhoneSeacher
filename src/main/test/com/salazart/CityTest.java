package com.salazart;

import com.salazart.db.models.City;
import com.salazart.db.services.CityService;

public class CityTest {
	private static final long id = 1705919;
	public static void main(String[] args) {
		CityService cityService = new CityService();
		City city = new City(id, "");
		city = cityService.getById(city);
		System.out.println(city.toString());
	}
}
