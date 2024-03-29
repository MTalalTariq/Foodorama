package javPKG;

import java.math.BigDecimal;

/**
 * Defines the name and price of a menu item. Price is stored as a BigDecimal to
 * avoid rounding errors.
 *
 * @author Muhammad Talal Tariq
 */
public class MenuItem {

	// Attributes
	private static final String itemFormat = "%-12s $%5.2f";
	private String name = null;
	private BigDecimal price = null;

	/**
	 * Constructor. Must set price to 2 decimal points for calculations.
	 *
	 * @param name  Name of the menu item.
	 * @param price Price of the menu item.
	 */
	public MenuItem(final String name, final BigDecimal price) {

		//
		this.name = name;
		this.price = price;

	}

	/**
	 * Alternate constructor. Converts a double price to BigDecimal.
	 *
	 * @param name  Name of the menu item.
	 * @param price Price of the menu item.
	 */
	public MenuItem(final String name, final double price) {

		//
		this.name = name;
		this.price = java.math.BigDecimal.valueOf(price);
	}

	/**
	 * name getter
	 *
	 * @return Name of the menu item.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * price getter
	 *
	 * @return Price of the menu item.
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * Returns a MenuItem as a String in the format:
	 *
	 * 
	 * hot dog $ 1.25 pizza $10.00
	 * 
	 */
	@Override
	public String toString() {

		//

		String str = String.format("%-13s$%5.2f", this.name, this.price);
		// String str1 = str + this.price;
		return str;
	}
}