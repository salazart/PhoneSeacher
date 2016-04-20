package com.salazart.eshko;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Eshko {
	private static final int COUNT_CHIELD_NODE = 1;
	private static final String SELECTOR = "p";
	private String textUrl;

	public Eshko(String textUrl) {
		this.textUrl = textUrl;
	}
	
	private Elements getElements(){
		try {
			URL url = new URL(textUrl);
			Document doc = Jsoup.parse(url, 1000);
			return doc.select(SELECTOR);
		} catch (IOException e) {
			return null;
		}
	}
	
	public List<String> getMessagesByText(String text){
		Elements links = getElements();
		if(links == null){
			return new ArrayList<String>();
		} else {
			return findByText(links, text);
		}
	}

	private List<String> findByText(Elements elements, String text) {
		List<String> messages = new ArrayList<String>();
		for (Element element : elements) {
			if(StringUtils.containsIgnoreCase(element.ownText(), text) 
					&& element.childNodeSize() == COUNT_CHIELD_NODE){
				messages.add(element.ownText());
			}
		}
		return messages;
	}
	
}
