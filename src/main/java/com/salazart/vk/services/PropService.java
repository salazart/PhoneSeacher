package com.salazart.vk.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class created for work with file config.properties
 * @author salazart
 *
 */
public class PropService {
	private final static String PATH_PROPERTIES = "/src/main/resources/config.properties";
	private static String fileName = System.getProperty("user.dir") + PATH_PROPERTIES;
	
	public static void setValue(String typeProperties, String valueProperties) {
		Properties properties = readFromFile();
		properties.setProperty(typeProperties, valueProperties);
		writeToFile(properties);
	}

	private static void writeToFile(Properties properties) {
		createDirectory();
			
		try {
			properties.store(new FileOutputStream(fileName), null);
			System.err.println("File saved successfully: " + fileName);
		} catch (IOException e) {
			System.err.println(e);
		} 
	}

	private static void createDirectory() {
		File parentFolder = new File(new File(fileName).getParent());
		if(!parentFolder.isDirectory()){
			if(parentFolder.mkdirs()){
				System.err.println("Folder created successfully: " + parentFolder);
			};
		}
	}

	private static Properties readFromFile() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(fileName));
		} catch (Exception e) {
			System.err.println(e);
		}
		return properties;
	}

	public static String getValue(String typeProperties) {
		String valueProperties = readFromFile().getProperty(typeProperties);
		if (valueProperties == null) {
			setValue(typeProperties, "");
			return "";
		} else {
			return valueProperties;
		}
	}
}
