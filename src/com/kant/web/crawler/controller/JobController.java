/**
 * 
 */
package com.kant.web.crawler.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.kant.web.crawler.model.JobFactory;
import com.kant.web.crawler.model.JobRunnable;
import com.kant.web.crawler.model.QueueMessage;
import com.kant.web.crawler.strategy.UrlParserStrategy;

/**
 * @author shashi
 * 
 */
public class JobController implements CrawlerController {
	private ExecutorService executorService;
	private BlockingQueue<QueueMessage> sharedQueue;
	private UrlParserStrategy parser;
	private final int endLevel = 5; // TODO make it configurable
	private Set<String> urlsCache;

	/**
	 * @param executorService
	 * @param sharedQueue
	 * @param executor
	 */
	public JobController(ExecutorService executorService,
			BlockingQueue<QueueMessage> sharedQueue, UrlParserStrategy parser) {
		this.executorService = executorService;
		this.sharedQueue = sharedQueue;
		this.parser = parser;
		urlsCache = new HashSet<String>();

	}

	/**
	 * Processes only 3000 urls.
	 * 
	 * TODO make processing power configurable
	 * 
	 * @param baseUrl
	 * @throws InterruptedException
	 */
	public void processWebSite(String baseUrl) {
		try {
			sharedQueue.put(JobFactory.createJob(baseUrl, 1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 3000; i++) {
			QueueMessage message;
			try {
				message = sharedQueue.poll(400, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				message = null;
			}
			if (message != null) {
				if (message.getLevel() < endLevel) {
					if (!urlsCache.contains(message.getUrl())) {
						urlsCache.add(message.getUrl());
						executorService.execute(new JobRunnable(message,
								parser, sharedQueue));
					}
				} else if (message.getLevel() == endLevel) {
					System.out.println("[Processed/completed]: "
							+ message.getUrl() + " [At depth]: " + endLevel);
				}

			}
			System.out.println("\n-------------" + i + "-------------\n");
		}
	}
}
