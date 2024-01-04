package javPKG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * 
 * 1.25 hot dog 10.00 pizza ...
 * 
 *
 * @author Muhammad Talal Tariq
 */
public class Menu {

	// Attributes.
	// define a List of MenuItem objects

	//
	// private Collection<MenuItem> items;// = new Collection<MenuItem>();
	private Collection<MenuItem> items = new ArrayList<MenuItem>();

	/**
	 * Creates a new Menu from an existing Collection of MenuItems. MenuItems are
	 * copied into the Menu List.
	 *
	 * @param items an existing Collection of MenuItems.
	 */
	public Menu(Collection<MenuItem> items) {

		//
		this.items = items;

	}

	/**
	 * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
	 * corresponds to a MenuItem. You have to read the Scanner line by line and add
	 * each MenuItem to the List of items.
	 *
	 * @param fileScanner A Scanner accessing MenuItem String data.
	 */
	public Menu(Scanner fileScanner) {

		//

		while (fileScanner.hasNextLine()) {

			String line = fileScanner.nextLine();
			// System.out.println(line);

			String[] lineArr = line.split(" ", 2);
			// System.out.println(lineArr[0] + " : " + lineArr[1]);
			MenuItem men = new MenuItem(lineArr[1], Double.valueOf(lineArr[0]));
			this.items.add(men);
		}

	}

	/**
	 * Returns the List's i-th MenuItem.
	 *
	 * @param i Index of a MenuItem.
	 * @return the MenuItem at index i
	 */
	public MenuItem getItem(int i) {

		//

		return ((ArrayList<MenuItem>) this.items).get(i);
	}

	/**
	 * Returns the number of MenuItems in the items List.
	 *
	 * @return Size of the items List.
	 */
	public int size() {

		//

		return this.items.size();
	}

	/**
	 * Returns the Menu items as a String in the format:
	 *
	 * 
	 * 5) poutine $ 3.75 6) pizza $10.00
	 * 
	 *
	 * where n) is the index + 1 of the MenuItems in the List.
	 */
	@Override
	public String toString() {

		//
		String str = "";
		int cou = 1;
		for (MenuItem men : this.items) {
			str += cou + ") " + men.toString() + String.format("\n");
			cou++;
		}
		return str;
	}
}