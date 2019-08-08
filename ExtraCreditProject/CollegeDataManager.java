/**
 * CollegeDataManager reads the .csv file and creates College objects that are later 
 * stored in an Array. In addition, the class should print the data from the array 
 * as a table.
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package ExtraCreditProject;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class CollegeDataManager {
	private int numColleges = 0;
	private College[] colleges;
	private static String[] description;
	private static CollegeDataManager cdm;
	private static CollegeHashMap chm;
	
	/**
	 * Default Constructor 
	 */
	public CollegeDataManager() {
		numColleges = 0;
		colleges = new College[0];
		description = new String[0];
	}
	
	/**
	 * Uses the File class to read the .csv file and store the information 
	 * in the array College[] colleges.
	 * 
	 * @param location of the file
	 * @return CollegeDataManager
	 * @throws IllegalArgumentException:: if the data entered is in the wrong 
	 * 	format. For example, there is a letter in the average SAT score
	 * @throws FileNotFoundException:: when file is not found
	 */
	public static CollegeHashMap buildFromFile(String location) 
			throws IllegalArgumentException, FileNotFoundException {
		cdm = new CollegeDataManager();
		chm = new CollegeHashMap();
		
		try {
			// Start array of Colleges
			cdm.ensureCapacity(1);
			
			// Scan CSV for description header and Colleges
			File file = new File(location);
			Scanner scanCSV = new Scanner(file);
			String tableHeader = scanCSV.nextLine();
			cdm.description = tableHeader.split(",");
			
			// Trims the extra spacing in .csv file's table heading
			for (int i = 0; i < cdm.description.length; i++) {
				cdm.description[i] = cdm.description[i].trim();
			}
			
			// Splits values of the College Statistics (line in file)
			while (scanCSV.hasNextLine()) {
				String[] values = scanCSV.nextLine().split(",");
				
				for (int i = 0; i < values.length; i++) {
					values[i] = values[i].trim();
				}
				
				// Uses the individual values to make College object
				College newCollege = new College(values[0], values[1], values[2], 
						Integer.parseInt(values[3]), Double.parseDouble(values[4]),
						Integer.parseInt(values[5]));
				cdm.addCollege(newCollege);
			}
			
			cdm.ensureCapacity(1);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (CollegeAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return chm;
	}
	
	/**
	 * Adds the College object in the data structure chosen in the correct 
	 * alphabetical order that the list is in. If the array is full, create 
	 * a new array with more space and copy elements in the other array to 
	 * this array. Use the bigger array as the new array. 
	 * 
	 * @param name, name of College
	 * @param newCollege, College to be added
	 * @return nothing
	 * @throws CollegeAlreadyExistsException:: Thrown if a college with the 
	 * 	same statistics already exists in the list.
	 */
	public void addCollege(College newCollege) throws CollegeAlreadyExistsException {
		boolean sameCollege = false;
		int i = 0;
		
		while ((!sameCollege) && (i < numColleges) && (numColleges > 0)) {
			// Checks if the college is the same in the array of colleges
			if (this.colleges[i].equals(newCollege)) {
				sameCollege = true;
				break;
			}
			
			i++;
		}
	}
	
	/**
	 * Removes the College from the data structure chosen. Once the college
	 * is removed, move all the colleges after that location in the array to one space left.
	 * 
	 * @param name, name of College
	 * @throws CollegeDoesNotExistException:: Thrown if a college with the given name does not exist.
	 */
	public void removeCollege(String name) throws CollegeDoesNotExistException {
		int index = indexOf(name);
		
		// If College does not exist, throw exception
		if (index == -1) {
			throw new CollegeDoesNotExistException("College does not exist.");
		} else {
			// Shifts each value to the left and trims the array of colleges by one
			arrayCopy(colleges, index + 1, colleges, index, colleges.length - 1);
			numColleges--;
			trimToSize();
		}
	}
	
	/**
	 * Retrieves and prints the data of the College object from the data structure chosen.
	 * 
	 * @param name, Name of the College object to be printed.
	 * @throws CollegeDoesNotExistException:: Thrown if a college with the given name does not exist.
	 */
	public void getCollege(String name) throws CollegeDoesNotExistException {
		int index = indexOf(name);
		
		// If College does not exist, throw exception
		if (index == -1) {
			throw new CollegeDoesNotExistException("College does not exist.");
		} else {
			System.out.println(colleges[index].toString());
		}
	}
	
	/**
	 * Helper Method for both getCollege and removeCollege to find its index in the array
	 * 
	 * @param name, Name of College object to be found
	 * @return index of College in array
	 */
	public int indexOf(String name) {
		for (int i = 0; i < numColleges; i++) {
			if (colleges[i].getName().equals(name)) {
				return i;
			}
		}
		// Person does not exist
		return -1;
	}
	
	/**
	 * Prints the CollegeDataManager in tabular form. 
	 * @return nothing.
	 */
	public void printTable() {
		sort();
		for (int i = 0; i < description.length; i++) {
			if (i != description.length - 1) {
				System.out.printf("%-26s|", description[i]);
			} else {
				System.out.printf("%-26s", description[i]);
			}
		}
		
		System.out.println();
		System.out.println("=========================================================================================================================");
		
		for (int i = 0; i < colleges.length; i++) {
			if (colleges[i] != null) {
				System.out.printf("%-26s|%-26s|%-26s|%-26s|%-26s|%-26s%n", colleges[i].getName());
			}
		}
	}
	
	/**
	 * Sorts by name alphabetically using bubble sort
	 * 
	 *  @param nothing
	 *  @return nothing
	 */
	public void sort() {
		if (numColleges > 1) {
			for (int j = 0; j < numColleges; j++) {
				for (int k = 0; k < numColleges - j - 1; k++) {
					if (this.colleges[k].compareTo(this.colleges[k+1]) > 0) {
						College temp = colleges[k];
						colleges[k] = colleges[k+1];
						colleges[k+1] = temp;
					}
				}
			}
		}
	}
	
	/**
	 * Extra Credit
	 * Sorts by either name, location, major, satScore, gpa, percentAdmit
	 * 
	 * @param sortOpt, the sorting option user requests
	 * @return nothing
	 */
	public void sort(char sortOpt) {
		ArrayList<College> collegesList = (ArrayList<College>) toArrayList(colleges);
		switch (sortOpt) {
			case 'N':
				Collections.sort(collegesList, new NameComparator());
				break;
			
			case 'L':
				Collections.sort(collegesList, new LocationComparator());
				break;
				
			case 'M':
				Collections.sort(collegesList, new MajorComparator());
				break;
				
				
			case 'X':
				Collections.sort(collegesList, new SATComparator());
				break;
				
			case 'Y':
				Collections.sort(collegesList, new GPAComparator());
				break;
				
			case 'Z':
				Collections.sort(collegesList, new PercentAdmitComparator());
		}
	}
	
	/**
	 * Makes sure the the array holds all values of colleges
	 * 
	 * @param minCapacity, minimum capacity of the database
	 * @return nothing
	 */
	public void ensureCapacity(int minCapacity) {
		// If there's not enough space in data structure, make a bigger array
		if (colleges.length < minCapacity) {
			College[] biggerArr = new College[minCapacity];
			arrayCopy(colleges, 0, biggerArr, 0, numColleges);
		}
	}
	
	/**
	 * Trims the array size to the number of elements are in it
	 * 
	 * @return nothing. 
	 */
	public void trimToSize() {
		College[] trimmedArr;
		if (colleges.length != numColleges) {
			trimmedArr = new College[numColleges];
			arrayCopy(colleges, 0, trimmedArr, 0, numColleges);
		}
	}
	
	/**
	 * Copies an array
	 * @param src array of the source to be copied from
	 * @param srcPos of the index to start copying from src array
	 * @param dest array to be copied to
	 * @param desPos of the index to start being copied to
	 * @param length to which to be copied
	 * 
	 * @return nothing
	 */
	public void arrayCopy(College[] src, int srcPos, College[] dest, int desPos, int length) {
		for (int i = desPos; i < length; i++, srcPos++) {
			dest[i] = src[srcPos];
		}
	}

	/**
	 * Helper function for Sorting (Extra Credit)
	 * Converts Array to ArrayList
	 * 
	 * @param colleges, college array
	 * @return nothing
	 */
	public List<College> toArrayList(College[] colleges) {
		return Arrays.asList(colleges);
	}
}
