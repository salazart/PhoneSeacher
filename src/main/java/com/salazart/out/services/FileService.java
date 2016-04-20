package com.salazart.out.services;

import java.util.Calendar;

import com.salazart.out.models.TelBook;


public class FileService {

	public void saveFile(TelBook telBook){
		String fileName = getFileName();
		
		XmlService xmlService = new XmlService();
		String xmlFileName = xmlService.createXmlFile(telBook, fileName);
		
		ZipService zipService = new ZipService(xmlFileName);
		zipService.createZipFile();
	}
	
	private String getFileName(){
		Calendar calendar = Calendar.getInstance();
		
		String fileName = "tel" + calendar.get(Calendar.YEAR)
				+ calendar.get(Calendar.MONTH) 
				+ calendar.get(Calendar.DAY_OF_MONTH)
				+ calendar.get(Calendar.HOUR_OF_DAY)
				+ calendar.get(Calendar.MINUTE)
				+ calendar.get(Calendar.SECOND);
		return fileName;
	}
}