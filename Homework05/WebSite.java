/**
 * 
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package Homework05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WebSite {
	private WebPage homepage;
	private WebPage cursor;
	
	public WebSite() {
		homepage = null;
		cursor = null;
	}

	/**
	 * @return the cursor
	 */
	public WebPage getCursor() {
		return cursor;
	}

	/**
	 * @param cursor the cursor to set
	 */
	public void setCursor(WebPage cursor) {
		this.cursor = cursor;
	}

	/**
	 * @return the homepage
	 */
	public WebPage getHomepage() {
		return homepage;
	}

	/**
	 * @param homepage the homepage to set
	 */
	public void setHomepage(WebPage homepage) {
		this.homepage = homepage;
	}
	
	/**
	 * Adds a new Department to WebSite
	 * @param WebPage cursor to traverse through tree
	 * @param name of department
	 * @return nothing
	 * @throws PageAlreadyExistsException if site already exists 
	 */
	public void addDepartment(String name) throws PageAlreadyExistsException {
		WebPage department = new WebPage(name, this.getHomepage());
		
		if (cursor != null) {
			department.setParentPage(cursor);
			cursor = department;
		} else {
			throw new PageAlreadyExistsException();
		}
	}
	
	/**
	 * Removes department from WebSite 
	 */
	public void removeDepartment() throws PageDoesNotExistException {
		this.setCursor(null);
	}
	
	public void printFormat() {
		
	}
	
	/**
	 * Builds tree from file
	 * @param String location, location of file
	 * @return WebSite built from WebPage tree
	 * @throws FileNotFoundException when file is not found
	 */
	public WebSite buildTree(String location) throws FileNotFoundException {
		WebSite ws = new WebSite();
		try {
			File file = new File(location);
			Scanner scanTxt = new Scanner(file);
		
			// Traverses through file, taking in data of WebSite
			while (scanTxt.hasNextLine()) {
				// Splits the data into the link index with the corresponding department
				String[] line = scanTxt.nextLine().split(" ");
				
				// If pageIndex is 0, it's the website homepage
				if (line[0].equals("0")) {
					ws.homepage = new WebPage(line[1]); // Root WebPage
				} else if (line[0].length() == 2) { // Head of Department
					try {
						WebPage newPage = new WebPage(line[1]);
						newPage.setParentPage(homepage);
						homepage.getLinks()[Integer.parseInt(line[0])] = newPage;
						addDepartment(line[0]);
					} catch (PageAlreadyExistsException e) {
						e.getMessage();
					}
					
				} else if (line[0].length() == 3) { // SubDepartment
					try {
						WebPage newPage = new WebPage(line[1]);
						homepage.getLinks()[Integer.parseInt(line[0])] = newPage;
						addDepartment(line[1]);
					} catch (PageAlreadyExistsException e) {
						e.getMessage();
					}
					
				} else {
					break;
				}
			}
			scanTxt.close();
		} catch (FileNotFoundException e) {
			System.out.println("Incorrect File! Please try again.\n");
		}
		return ws;
	}
}
