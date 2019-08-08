/**
 * 
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
package Homework05;
public class WebPage {
	private String name;
	private int numPages;
	private WebPage parentPage;
	private WebPage[] links;
	
	// Default Constructor
	public WebPage() {
		name = null;
		numPages = 0;
		parentPage = null;
		links = new WebPage[100];
	}

	/**
	 * Specified Constructor
	 * @param String name of WebPage 
	 */
	public WebPage(String name) {
		this.name = name;
		numPages = 0;
		parentPage = null;
		links = new WebPage[100];
	}
	
	/**
	 * Specified Constructor
	 * @param String name of WebPage
	 */
	public WebPage(String name, WebPage parentPage) {
		this.name = name;
		numPages = 0;
		parentPage = null;
		links = new WebPage[100];
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the numPages
	 */
	public int getNumPages() {
		return numPages;
	}

	/**
	 * @param numPages the numPages to set
	 */
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	/**
	 * @return the parentPage
	 */
	public WebPage getParentPage() {
		return parentPage;
	}

	/**
	 * @param parentLink the parentPage to set
	 */
	public void setParentPage(WebPage parentPage) {
		this.parentPage = parentPage;
	}
	
	/**
	 * @return the links
	 */
	public WebPage[] getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(WebPage[] links) {
		this.links = links;
	}
	
	/**
	 * @param WebPage target of cursor	 
	 */
	public int findCursor(WebPage target) {
		WebPage[] tempLink = this.getLinks();
		for (int i = 0; i < tempLink.length; i++) {
			if (tempLink[i].getName().compareToIgnoreCase(target.getName()) != 0) {
				return tempLink[i].getName().charAt(i) - target.getName().charAt(i);
			}
		}
		return 0;
	}
}
