/**
 * 
 */
package com.kant.web.crawler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.kant.web.crawler.controller.CrawlerController;
import com.kant.web.crawler.controller.JobController;
import com.kant.web.crawler.model.QueueMessage;
import com.kant.web.crawler.strategy.JsoupExecuter;
import com.kant.web.crawler.strategy.UrlParserStrategy;

/**
 * @author shashi
 * 
 */
public class Test {

	public static void main(String[] args) throws Exception {

		ExecutorService executorService = Executors.newFixedThreadPool(8);
		BlockingQueue<QueueMessage> sharedQueue = new LinkedBlockingQueue<QueueMessage>(
				200);
		UrlParserStrategy parser = new JsoupExecuter();
		CrawlerController controller = new JobController(executorService,
				sharedQueue, parser);

		controller.processWebSite("http://www.google.com/");
		System.out.println("calling shutdown hook !!");
		// Thread.sleep(120000);
		// executorService.shutdownNow();
		executorService.shutdown();
		// Wait until all threads are finish
		while (!executorService.isTerminated()) {
		}
		System.out.println("\nFinished all threads");

	}
	// BufferedReader reader = null;
	// try {
	// URL url = new URL("http://www.google.com");
	// reader = new BufferedReader(new InputStreamReader(url.openStream()));
	// String line;
	// while ((line = reader.readLine()) != null) {
	// // System.out.println(line);
	// processUrl(line);
	// break;
	// }
	// reader.close();
	// } catch (IOException ioe) {
	// ioe.printStackTrace();
	// }

	// private static void processUrl(String line) throws Exception {
	//
	// URL url = new URL(
	// "http://stackoverflow.com/questions/5801993/quickest-way-to-get-content-type");
	// HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	// connection.setRequestMethod("HEAD");
	// connection.connect();
	// String contentType = connection.getContentType();
	// System.out.println(contentType.contains("text/html;")
	// || contentType.contains("application/xml"));
	// connection.disconnect();
	//
	// Document doc;
	// try {
	// Response resp = Jsoup
	// .connect(
	// "http://192.168.1.104/files/47%20Ronin%20(2013)/47.Ronin.2013.720p.BluRay.x264.YIFY.mp4")
	// .timeout(10 * 1000).execute();
	// contentType = resp.contentType();
	// System.out.println(contentType);
	//
	// // doc = Jsoup
	// // .connect(
	// //
	// "http://192.168.1.104/files/47%20Ronin%20(2013)/47.Ronin.2013.720p.BluRay.x264.YIFY.mp4")
	// // .timeout(10 * 1000).get();
	// doc = resp.parse();
	// Elements links = doc.select("a[href]");
	// System.out.printf("\nLinks: (%d)", links.size());
	// for (Element link : links) {
	// System.out.printf(" * a: <%s>  (%s)\n", link.attr("abs:href"),
	// link.text().trim());
	// }
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// doc = null;
	// }
	//
	// // String re1 = "(href)"; // Variable Name 1
	// // String re2 = "(=)"; // Any Single Character 1
	// // String re3 = "(\")"; // Any Single Character 2
	// // String re4 =
	// // "((?:http|https)(?::\\/{2}[\\w]+)(?:[\\/|\\.]?)(?:[^\\s\"]*))"; //
	// // HTTP
	// // String re5 = "(\")"; // URL
	// // // 1
	// //
	// // Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5,
	// // Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	// // Matcher m = p.matcher(line);
	// // if (m.find()) {
	// // System.out.println(m.group());
	// // }
	// }
}
