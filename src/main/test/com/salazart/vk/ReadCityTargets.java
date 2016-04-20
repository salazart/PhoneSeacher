package com.salazart.vk;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ReadCityTargets {

	public static void main(String[] args) {
		String path = "src/main/resources/CityTargets.txt";
		try {
			List<String> cityTargets = Files.readAllLines(new File(path).toPath());
			for (String cityTarget : cityTargets) {
				System.out.println(cityTarget);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
