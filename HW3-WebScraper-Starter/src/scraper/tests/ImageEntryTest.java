// COURSE: CSCI1620
// TERM: Fall 2019
//
// NAME: Matt Csukker
// RESOURCES: No help was used for this class, only in WebScraper did I get help.

package scraper.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import scraper.base.ImageEntry;
import scraper.base.WebScraper;

/**
 * Test class for ImageEntry.
 * @author mattcsukker
 */
public class ImageEntryTest 
{
	/**
	 * Tests the constructor.
	 */
	@Test
	public void testImageEntryConstructor()
	{
		ImageEntry a = new ImageEntry("http://cool.story.com/page2.html", "pic1c.png");
		a.setImgLocation("pic1c.png");
		a.setPageLocation("http://cool.story.com/page2.html");
		assertEquals("pic1c.png", a.getImgLocation());
		assertEquals("http://cool.story.com/page2.html", a.getPageLocation());
	}
	
	/**
	 * Tests the setPageLocation method when null.
	 */
	@Test
	public void testImageEntrySetPageLocationNull()
	{
		ImageEntry a = new ImageEntry("http://cool.story.com/page2.html", "pic1c.png");
		a.setPageLocation(null);
		assertEquals(null, a.getPageLocation());
	}
	
	
	/**
	 * Tests the setPageLocation method when empty.
	 */
	@Test
	public void testImageEntrySetPageLocationEmpty()
	{
		ImageEntry a = new ImageEntry("http://cool.story.com/page2.html", "pic1c.png");
		a.setPageLocation("");
		assertEquals(null, a.getPageLocation());
	}
	
	/**
	 * Tests the setImgLocation method when null.
	 */
	@Test
	public void testImageEntrySetImgLocationNull()
	{
		ImageEntry a = new ImageEntry("http://cool.story.com/page2.html", "pic1c.png");
		a.setImgLocation(null);
		assertEquals(null, a.getImgLocation());
	}
	
	/**
	 * Tests the setImgLocation method when empty.
	 */
	@Test
	public void testImageEntrySetImgLocationEmpty()
	{
		ImageEntry a = new ImageEntry("http://cool.story.com/page2.html", "pic1c.png");
		a.setImgLocation("");
		assertEquals(null, a.getImgLocation());
	}
	
	/**
	 * Tests the equals method when true.
	 */
	@Test
	public void testImageEntryEqualsTrue()
	{
		ImageEntry a = new ImageEntry("http://cool.story.com/page2.html", "pic1c.png");
		ImageEntry b = new ImageEntry("http://cool.story.com/page2.html", "pic1c.png");
		a.equals(b);
		assertEquals(true, a.equals(b));
	}
	
	/**
	 * Tests the equals method when false.
	 */
	@Test
	public void testImageEntryEqualsFalse()
	{
		ImageEntry a = new ImageEntry("http://cool.story.com/page2.html", "pic1c.png");
		ImageEntry b = new ImageEntry("http://cool.story.com/page2.html", "urmom.png");
		a.equals(b);
		assertEquals(false, a.equals(b));
	}
	
	/**
	 * Tests the equals method when one object is not an instance of another.
	 */
	@Test
	public void testImageEntryEqualsFalseNotAnInstance()
	{
		ImageEntry a = new ImageEntry("http://cool.story.com/page2.html", "pic1c.png");
		WebScraper b = new WebScraper("http://cool.story.com/page2.html");
		a.equals(b); //supposed to be unrelated to each other
		assertEquals(false, a.equals(b)); //supposed to be unrelated to each other
	}
	 
	/**
	 * Tests the toString method.
	 */
	@Test
	public void testImageEntryToString()
	{
		ImageEntry a = new ImageEntry("http://cool.story.com/page2.html", "pic1c.png");
		a.setImgLocation("pic1c.png");
		a.toString();
		assertEquals("pic1c.png", a.toString());
	}	
}
