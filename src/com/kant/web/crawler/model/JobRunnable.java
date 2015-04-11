/**
 * 
 */
package com.kant.web.crawler.model;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.kant.web.crawler.strategy.UrlParserStrategy;

/**
 * @author shashi
 * 
 */
public class JobRunnable implements Runnable {
	private UrlParserStrategy parser;
	private QueueMessage job;
	private BlockingQueue<QueueMessage> sharedQ;

	public JobRunnable(QueueMessage job, UrlParserStrategy parser,
			BlockingQueue<QueueMessage> queueRef) {
		this.parser = parser;
		this.job = job;
		this.sharedQ = queueRef;
	}

	/**
	 * 
	 */
	public void run() {
		String url = job.getUrl();
		if (url != null && !url.isEmpty()) {
			System.out.println("[Processing]: " + url + " [At depth]: "
					+ job.getLevel());
			List<String> result = parser.fetchLinks(url);
			int newLevel = job.getLevel() + 1;
			if (result != null) {
				for (String item : result) {
					if (item != null && !item.isEmpty()) {
						try {
							// sharedQ.put(JobFactory.createJob(item,
							// newLevel));
							sharedQ.offer(JobFactory.createJob(item, newLevel),
									100, TimeUnit.MILLISECONDS);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			System.out.println("[Completed]: " + url);
		}
	}
}
