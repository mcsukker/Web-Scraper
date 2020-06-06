// COURSE: CSCI1620
// TERM: Fall 2019
//
// NAME: Matt Csukker
// RESOURCES: Brianna from the CS learning center helped me with my getImagesHelper method.

package scraper.base;

import scraper.utils.ResultSet;
import scraper.utils.Document;
import scraper.utils.Element;
import scraper.utils.Elements;
import scraper.utils.PageHistory;

/**
 * This class provides a simple mechanism to crawl a series of webpages recursively and extract all of the images
 * that are found on the pages visited.
 * @author mattcsukker
 */
public class WebScraper 
{
	/**
	 * String of the url.
	 */
	private java.lang.String inUrl;
	 
	/**
	 * The recursive depth explored.
	 */
	private int depth;
	
	/**
	 * The object used from the ResultSet class.
	 */
	private scraper.utils.ResultSet images = new ResultSet();
	
	/**
	 * The object used from the PageHistory class.
	 */
	private scraper.utils.PageHistory visitedPages = new PageHistory();
	
	/**
	 * Builds a new WebScraper that should start at the provided URL and will by default explore at a depth of 0.
	 * This allows extracting just the details from this page and nothing else.
	 * @param urlIn - The URL to explore for images.
	 */
	public WebScraper(java.lang.String urlIn)
	{
		inUrl = urlIn;
	}
	
	/**
	 * Builds a new WebScraper that should start at the provided URL and will explore recursively 
	 * to a specified length.
	 * @param urlIn - The URL to begin exploring for images.
	 * @param depthIn - The recursive depth to explore, must be >= 0. Negative values will be treated as 
	 * equivalent to 0.
	 */
	public WebScraper(java.lang.String urlIn, int depthIn)
	{
		setURL(urlIn);
		setDepth(depthIn);
	}
	
	/**
	 * Updates this WebScraper to explore a new depth.
	 * @param depthIn - The recursive depth to explore, must be >= 0. Negative values will be treated as
	 * equivalent to 0.
	 */
	public void setDepth(int depthIn)
	{
		if (depthIn < 0)
		{
			depth = 0; 
		}
		else  
		{
			depth = depthIn;
		}
	}
	
	/**
	 * Retrieves the exploration depth of this WebScraper.
	 * @return The depth stored in this WebScraper.
	 */
	public int getDepth()
	{
		return depth;
	}
	
	/**
	 * Updates the base URL to explore for this WebScraper.
	 * @param url - The new URL to explore. The WebScraper URL is only changed if the URL value is valid
	 * (not null or empty).
	 */
	public void setURL(java.lang.String url)
	{
		if (url != null && !url.isEmpty())
		{
			inUrl = url;
		}
	}
	
	/**
	 * Retrieves the base URL for exploration by this WebScraper.
	 * @return The base url.
	 */
	public java.lang.String getURL()
	{
		return inUrl;
	}
	
	/**
	 * Retrieves the set of images found directly at the initial base URL for this WebScraper.  This method
	 * will explore any links found at the base page. Image information should be stored in the order in which 
	 * their corresponding <img> tags appear in the source HTML code. If an image appears more than once in 
	 * a page, only one entry should appear in the ResultSet.
	 * @return A collection of ImageEntry objects for the images found at the base URL location.
	 */
	public scraper.utils.ResultSet getImages()
	{
		
		return getImagesHelper(inUrl);
	}
	
	/**
	 * The Helper method being used for getImages.
	 * @param website - The string URL.
	 * @return ResultSet object images.
	 */
	public ResultSet getImagesHelper(java.lang.String website)
	{
		scraper.utils.Document webPage = new Document(); 
		ResultSet scraping = new ResultSet(); 
		webPage.loadPageFromURL(website);
		if (!visitedPages.visited(website))
		{
			visitedPages.markVisited(website);
		}
		if (webPage.loadPageFromURL(website))
		{
			Elements imgDetails = webPage.getElementsByTag("img");
			while (imgDetails.hasNextElement())
			{
				Element add = imgDetails.getNextElement();
				scraping.addResult(new ImageEntry(website,
						((scraper.utils.Element) add).getAttributeValue("src")));
			}
		}
		images = images.merge(scraping); 
		/* Brianna helped explain to me the merge method above, I needed to have images 
		equal the merge because otherwise images would still be null, which was giving
		me a null pointer exception when I tried to test it. */
		return images; 
	}
	
	/**
	 * This method will recursively explore pages starting at the base URL defined for this WebScraper to the depth 
	 * for which the scraper is configured. The ResultSet will contain all images discovered along the way, with 
	 * images from a page being explored stored in the ResultSet prior to any images found on linked pages. 
	 * ImageEntries will always appear in the ResultSet in the order in which the corresponding <img> tags are given
	 * in the HTML files. Only the first occurrence of a particular image (by absolute URL) is stored in the 
	 * ResultSet.  Links on a page will be explored in the sequential order in which their corresponding < a > tags 
	 * appear in the HTML file. The same URL should never be crawled more than once, even if it is linked from 
	 * itself or another later page.
	 * @return The set of ImageEntry objects resulting from this recursive crawl.
	 */
	public scraper.utils.ResultSet crawlPage()
	{
		return crawlPageHelper(depth, inUrl); 
	}
	
	/**
	 * The helper method for crawlPage.
	 * @param depthIn - the depth for the number of pages crawled.
	 * @param website - the string for the URL.
	 * @return images
	 */
	public ResultSet crawlPageHelper(int depthIn, java.lang.String website)
	{ 
		Document site = new Document();
		if (depthIn == 0) // base case
		{
			site.loadPageFromURL(website);
			getImagesHelper(website);
			return images;
		}
		else // general case 
		{
			site.loadPageFromURL(website);
			if (!visitedPages.visited(website))
			{
				visitedPages.markVisited(website);
			}
			if (site.loadPageFromURL(website))
			{
				getImagesHelper(website);
				Elements tag = site.getElementsByTag("a");
				while (tag.hasNextElement())
				{
					String attribute = tag.getNextElement().getAttributeValue("href");
					if (!visitedPages.visited(attribute))
					{
						crawlPageHelper(depthIn - 1, attribute); // smaller caller question
					}
				}
			}
			
		}
		return images;  
	}
	
	/**
	 * Retrieves the historical trail of pages visited during the last top-level call to crawlPage.
	 * @return The most recent collection of pages visited. If the crawlPage method has not been previously called, 
	 * the PageHistory object will be empty.
	 */
	public scraper.utils.PageHistory getPageHistory()
	{
		// I used to have a helper method for getPageHistory, but Brianna told me I did not need it.
		return visitedPages;
	}
}

