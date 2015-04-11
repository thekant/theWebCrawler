/**
 * 
 */
package com.kant.web.crawler.model;

/**
 * @author shashi
 * 
 */
public abstract class QueueMessage implements Message {

	private int level;
	private String jobUrl;

	/**
	 * 
	 * @param level
	 * @param url
	 */
	public QueueMessage(int level, String url) {
		jobUrl = url;
		this.level = level;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kant.web.crawler.model.UrlMessage#getUrl()
	 */
	public String getUrl() {
		return jobUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kant.web.crawler.model.UrlMessage#setUrl(java.lang.String)
	 */
	public void setUrl(String url) {
		this.jobUrl = url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kant.web.crawler.model.UrlMessage#getLevel()
	 */
	public int getLevel() {
		return level;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kant.web.crawler.model.UrlMessage#setLevel()
	 */
	public void setLevel(int value) {
		this.level = value;
	}
}
