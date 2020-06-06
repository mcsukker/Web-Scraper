// COURSE: CSCI1620
// TERM: Fall 2019
//
// NAME: Matt Csukker
// RESOURCES: No help was used for this class, only in WebScraper did I get help. 

package scraper.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import scraper.base.ImageEntry;
import scraper.base.WebScraper;
import scraper.utils.PageHistory;
import scraper.utils.ResultSet;

/**
 * Test class for WebScraper.
 * @author mattcsukker
 */
public class WebScraperTest 
{
	/**
	 * Tests the constructor.
	 */
	@Test
	public void testWebScraperConstructor()
	{
		WebScraper a = new WebScraper("http://cool.story.com/page2.html");
		a.setURL("http://cool.story.com/page2.html");
		assertEquals("http://cool.story.com/page2.html", a.getURL());
	}
	
	/**
	 * Tests the other constructor.
	 */
	@Test
	public void testWebScraperConstructorOther()
	{
		WebScraper a = new WebScraper("http://cool.story.com/page2.html", 2);
		a.setDepth(4);
		a.setURL("http://cool.story.com/page2.html");
		assertEquals(4, a.getDepth());
		assertEquals("http://cool.story.com/page2.html", a.getURL());
	}
	
	/**
	 * Tests the setDepth method when the number is negative.
	 */
	@Test
	public void testWebScraperSetDepthNegative()
	{
		WebScraper a = new WebScraper("http://cool.story.com/page2.html", 2);
		a.setDepth(-12);
		assertEquals(0, a.getDepth());
	}
	
	/**
	 * Tests the setDepth method when the number is zero.
	 */
	@Test
	public void testWebScraperSetDepthZero()
	{
		WebScraper a = new WebScraper("http://cool.story.com/page2.html", 2);
		a.setDepth(0);
		assertEquals(0, a.getDepth());
	}
	
	/**
	 * Tests the setDepth method when the number is positive.
	 */
	@Test
	public void testWebScraperSetDepthPositive()
	{
		WebScraper a = new WebScraper("http://cool.story.com/page2.html", 2);
		a.setDepth(3);
		assertEquals(3, a.getDepth());
	}
	
	/**
	 * Tests the setURL method when valid.
	 */
	@Test
	public void testWebScraperSetUrlValid()
	{
		WebScraper a = new WebScraper("http://cool.story.com/page2.html", 2);
		a.setURL("http://cool.story.com/page2.html");
		assertEquals("http://cool.story.com/page2.html", a.getURL());
	}
	
	/**
	 * Tests the setURL method when null.
	 */
	@Test
	public void testWebScraperSetUrlNull()
	{
		WebScraper a = new WebScraper("http://cool.story.com/page2.html", 2);
		a.setURL(null);
		assertEquals("http://cool.story.com/page2.html", a.getURL());
	}
	
	/**
	 * Tests the setURL method when empty.
	 */
	@Test
	public void testWebScraperSetUrlEmpty()
	{
		WebScraper a = new WebScraper("http://cool.story.com/page2.html", 2);
		a.setURL("");
		assertEquals("http://cool.story.com/page2.html", a.getURL());
	}
	
	/**
	 * Tests the getImages method.
	 */
	@Test
	public void testWebScraperGetImages()
	{
		WebScraper a = new WebScraper("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page3.html");
		ResultSet b = a.getImages();
		ResultSet c = new ResultSet();
		ImageEntry topLeft = new ImageEntry("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page3.html", 
				"http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/imgs/puppy1.jpg");
		c.addResult(topLeft);
		ImageEntry topMiddle = new ImageEntry("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page3.html", 
				"http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/imgs/puppy5.jpg");
		c.addResult(topMiddle);
		ImageEntry topRight = new ImageEntry("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page3.html", 
				"http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/imgs/puppy2.jpg");
		c.addResult(topRight);
		ImageEntry bottomLeft = new ImageEntry("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page3.html", 
				"http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/imgs/puppy3.jpg");
		c.addResult(bottomLeft);
		ImageEntry bottomMiddle = new ImageEntry("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page3.html", 
				"http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/imgs/puppy4.jpg");
		c.addResult(bottomMiddle);
		ImageEntry bottomRight = new ImageEntry("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page3.html", 
				"http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/imgs/puppy6.jpg");
		c.addResult(bottomRight);
		assertTrue(c.getAllResults()[0].equals(b.getAllResults()[0]));
		assertTrue(c.getAllResults()[1].equals(b.getAllResults()[1]));
		assertTrue(c.getAllResults()[2].equals(b.getAllResults()[2]));
		assertTrue(c.getAllResults()[3].equals(b.getAllResults()[3]));
		assertTrue(c.getAllResults()[4].equals(b.getAllResults()[4]));
		assertTrue(c.getAllResults()[5].equals(b.getAllResults()[5]));
	}
	
	/**
	 * Tests the crawlPages method.
	 */
	@Test
	public void testWebScraperCrawlPages()
	{
		WebScraper t = new WebScraper("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page2.html", 2);
		PageHistory h = new PageHistory();
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page2.html");
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page1.html");
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page3.html");
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page0.html");
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/brokenlinkhereyay.html");
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/secretpage.html");
		t.crawlPage();
		assertArrayEquals(t.getPageHistory().getHistory(), h.getHistory());
	}

	/**
	 * Tests the crawlPages method.
	 */
	@Test
	public void testWebScraperCrawlPages2()
	{
		WebScraper t = new WebScraper("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page2.html", 2);
		PageHistory h = new PageHistory();
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page2.html");
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page1.html");
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page3.html");
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/page0.html");
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/brokenlinkhereyay.html");
		h.markVisited("http://loki.ist.unomaha.edu/~bdorn/csci1620/hw3testpage/secretpage.html");
		t.crawlPage();
		assertArrayEquals(t.getPageHistory().getHistory(), h.getHistory());
	}
}
