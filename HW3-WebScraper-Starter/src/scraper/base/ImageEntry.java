// COURSE: CSCI1620
// TERM: Fall 2019
//
// NAME: Matt Csukker
// RESOURCES: No help was used for this class, only in WebScraper did I get help.

package scraper.base;

/**
 * This class stores information about each image found while crawling pages. Image URLs are saved along with the URL
 * for the page on which the image appears.
 * @author mattcsukker
 */
public class ImageEntry 
{
	/**
	 * The web page URL.
	 */
	private java.lang.String pageAddressIn;
	
	/** 
	 * The image URL.
	 */
	private java.lang.String imgAddressIn;
	
	/**
	 * Creates a new ImageEntry storing an image URL alongside the source page URL.
	 * @param pageAddress - the URL for the page where the image was found
	 * @param imgAddress - the full URL of the image itself
	 */
	public ImageEntry(java.lang.String pageAddress, java.lang.String imgAddress)
	{
		pageAddressIn = pageAddress;
		imgAddressIn = imgAddress;
		setPageLocation(pageAddressIn);
		setImgLocation(imgAddressIn);
	}
	
	/**
	 * Retrieves the source page URL.
	 * @return The web page where the image of this ImageEntry can be found.
	 */
	public java.lang.String getPageLocation()
	{
		return pageAddressIn;
	}
	
	/**
	 * Sets the web page URL.
	 * @param webUrl - the valid web page URL.
	 */
	public void setPageLocation(java.lang.String webUrl)
	{
		if (webUrl != null && !webUrl.equals(""))
		{
			pageAddressIn = webUrl;
		}
		else
		{
			pageAddressIn = null;
		}
	}
	
	/**
	 * Retrieves the direct image URL.
	 * @return The full URL of image file itself.
	 */
	public java.lang.String getImgLocation()
	{
		return imgAddressIn;
	}
	
	/**
	 * Sets the web page URL.
	 * @param imgUrl - the valid image page URL.
	 */
	public void setImgLocation(java.lang.String imgUrl)
	{
		if (imgUrl != null && !imgUrl.isEmpty())
		{
			imgAddressIn = imgUrl;
		}
		else
		{
			imgAddressIn = null;
		}
	}
	
	/**
	 * Compares this ImageEntry to another object for equality based on the URL of the image file. 
	 * Two ImageEntries with the same URL but different page URLs will be considered equivalent.
	 * @param other - The other object that is being compared to this.
	 * @return true if this ImageEntry and other refer to the same image as file on the Web; 
	 * false otherwise
	 */
	public boolean equals(java.lang.Object other)
	{
		boolean result;
		if (other instanceof ImageEntry)
		{
			if (this.getImgLocation().equals(((ImageEntry) other).getImgLocation()))
			{
				result = true;
			}
			else
			{
				result = false;
			}
		}
		else 
		{
			result = false;
		}
		return result;
	}
	
	/**
	 * Retrieves a String version of this ImageEntry. The returned string should only contain the 
	 * URL for the image file.
	 * @return the image file URL from this ImageEntry
	 */
	public java.lang.String toString()
	{
		return String.format(getImgLocation());
	}	
}
