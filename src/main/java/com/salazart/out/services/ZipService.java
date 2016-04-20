package com.salazart.out.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang.StringUtils;

import com.salazart.vk.services.PropService;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ZipService {
	private static final String SAVE_PATH = "backUpPath";
	private String backUpPath = PropService.getValue(SAVE_PATH);
	
	private static final String FILE_ZIP= ".zip";
	
	private String currentFolder = System.getProperty("user.dir");
	
	private String zipFileName;
	
	private String inFileName;
	
	public ZipService(String inFileName) {
		this.inFileName = inFileName;
		this.zipFileName = backUpPath + StringUtils.substringBeforeLast(inFileName, ".") + FILE_ZIP;
	}
	
	public void createZipFile(){
		try (InputStream is = new FileInputStream(inFileName)){
			ZipFile zipFile = new ZipFile(zipFileName);
			ZipParameters zipParameters = createZipParameters();
			zipFile.addStream(is, zipParameters);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			deleteFile(inFileName);
		}
	}
	
	private void deleteFile(String fileName) {
		Path path = new File(fileName).toPath();
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private ZipParameters createZipParameters(){
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setFileNameInZip(inFileName);
		parameters.setSourceExternalStream(true);
		
		parameters.setEncryptFiles(true);
		parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
		
		parameters.setPassword(createName());
		return parameters;
	}
	
	public void extractZipFile(){
		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			if (zipFile.isEncrypted()) {
				zipFile.setPassword(createName());
			}
			zipFile.extractFile(inFileName, currentFolder);
		} catch (ZipException e) {
			System.out.println(e.getMessage());
		} finally {
			deleteFile(zipFileName);
		}
	}
	
	private String createName(){
		String name = "4";
		for(int i = 1; i < 3; i++){
			name = name + "mr3";
		}
		name = name +"89";
		return name;
	}
}
