/**
 * 
 */
package com.kant.web.crawler.model;

/**
 * @author shashi
 * 
 */
public class JobFactory {
	/**
	 * 
	 * @param url
	 * @param level
	 * @return
	 */
	public static QueueMessage createJob(String url, int level) {
		return new QJob(level, url);
	}
}
