/**
 * 
 */
package com.kant.web.crawler.strategy;

import java.util.List;

/**
 * @author shashi
 * 
 */
public interface UrlParserStrategy {
	/**
	 * 
	 * @param url
	 * @return
	 */
	public List<String> fetchLinks(String url);
}
