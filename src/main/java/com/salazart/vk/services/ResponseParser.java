package com.salazart.vk.services;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salazart.vk.models.response.CitiesGet;
import com.salazart.vk.models.response.FriendsGet;
import com.salazart.vk.models.response.UsersGet;

public class ResponseParser {
	private static final String GRILLE_SEPARATOR = "#";
	private static final String PROPERTY_SEPARATOR = "&";

	public String parseRequest(String url, String parameter) {
		url = StringUtils.substringAfter(url, GRILLE_SEPARATOR);

		List<String> properties = Arrays.asList(url.split(PROPERTY_SEPARATOR));
		Properties prop = new Properties();

		for (String string : properties) {
			try {
				prop.load(new StringReader(string));
			} catch (IOException e) {
				continue;
			}
		}
		String textProperty = prop.getProperty(parameter);
		return textProperty != null ? textProperty : "";
	}

	public FriendsGet parseJson(String content, FriendsGet friendsGet) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(content, friendsGet.getClass());
		} catch (IOException e) {
			return new FriendsGet();
		}
	}

	public UsersGet parseJson(String content, UsersGet usersGet) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(content, usersGet.getClass());
		} catch (IOException e) {
			return new UsersGet();
		}
	}

	public CitiesGet parseJson(String content, CitiesGet citiesGet) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(content, citiesGet.getClass());
		} catch (IOException e) {
			return new CitiesGet();
		}
	}
}
