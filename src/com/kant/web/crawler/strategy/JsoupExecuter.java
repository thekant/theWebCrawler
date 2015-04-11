/**
 * 
 */
package com.kant.web.crawler.strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author shashi
 * 
 */
public class JsoupExecuter implements UrlParserStrategy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.kant.web.crawler.strategy.ExecuterStrategy#execute(java.lang.String)
	 */
	public List<String> fetchLinks(String url) {
		if (url == null || url.isEmpty())
			return null;
		Document doc = null;
		List<String> urls = null;
		try {
			// if (isValid(url)) {
			doc = Jsoup.connect(url).timeout(3000).get();
			urls = new ArrayList<String>();
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				urls.add(link.attr("abs:href"));
			}
			// }
		} catch (IOException e) {
			System.out
					.println("[Skipping]:Non-textual webcontent or timeout has occured "
							+ url);
			urls = null;
		}
		doc = null;
		return urls;
	}

	/**
	 * for valid media types
	 */
	// private boolean isValid(String webUrl) throws IOException {
	// URL url = null;
	// HttpURLConnection connection = null;
	// try {
	// url = new URL(webUrl);
	// connection = (HttpURLConnection) url.openConnection();
	// connection.setRequestMethod("HEAD");
	// connection.connect();
	// String contentType = connection.getContentType();
	// return contentType.contains("text/html;")
	// || contentType.contains("application/xml");
	//
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } finally {
	// connection.disconnect();
	// }
	//
	// return false;
	// }
}
