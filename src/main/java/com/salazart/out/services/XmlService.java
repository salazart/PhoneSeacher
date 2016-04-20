package com.salazart.out.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.lang.StringUtils;

import com.salazart.out.models.Contact;
import com.salazart.out.models.TelBook;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XmlService{
	private static final String XML = ".xml";
	
	public String createXmlFile(TelBook telBook, String fileName) {
		String xmlText = createXmlContent(telBook);
		fileName = getXMLFileName(fileName);
		
		writeXMLFile(fileName, xmlText);
		
		return fileName;
	}
	
	public String createXmlContent(TelBook telBook){
		XStream xstream = new XStream(new StaxDriver());
		
		xstream.processAnnotations(TelBook.class);
	    xstream.processAnnotations(Contact.class);
		
	    return xstream.toXML(telBook);
	}
	
	public void writeXMLFile(String fileName, String xmlContent) {
		try (Writer out = new OutputStreamWriter(new FileOutputStream(fileName,false), "UTF8");  ){
			out.write(xmlContent);
		} catch (IOException e) {
			System.err.println(e);
		}		
	}
	
	private String getXMLFileName(String fileName){
		if(StringUtils.substringAfterLast(fileName, ".").equals(XML)){
			return fileName;
		} else {
			return fileName + XML; 
		}
	}
}
