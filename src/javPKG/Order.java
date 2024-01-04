package javPKG;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 * @author Muhammad Talal Tariq
 */
public class Order implements Printable {

	/**
	 * The current tax rate on menu items.
	 */
	public static final BigDecimal TAX_RATE = new BigDecimal(0.13);
	private Map<MenuItem, Integer> items = new HashMap<MenuItem, Integer>();

	// Attributes

	//

	/**
	 * Increments the quantity of a particular MenuItem in an Order with a new
	 * quantity. If the MenuItem is not in the order, it is added.
	 *
	 * @param item     The MenuItem to purchase - the HashMap key.
	 * @param quantity The number of the MenuItem to purchase - the HashMap value.
	 */
	public void add(final MenuItem item, final int quantity) {

		//
		int cou = 0;
		if (this.items.containsKey(item)) {
			cou = this.items.get(item);
		}
		if (cou + quantity > 0) {
			this.items.put(item, quantity + cou);
		}
	}

	/**
	 * Calculates the total value of all MenuItems and their quantities in the
	 * HashMap.
	 *
	 * @return the total price for the MenuItems ordered.
	 */
	public BigDecimal getSubTotal() {

		//

		// sub total
		BigDecimal sub = new BigDecimal((double) 0);
		// iteration
		for (Map.Entry<MenuItem, Integer> key : this.items.entrySet()) {
			MenuItem item = key.getKey();
			Integer quan = key.getValue();
			// item price
			BigDecimal cost = item.getPrice();
			// item price x quantity
			cost = cost.multiply(new BigDecimal((double) quan));
			sub = sub.add(cost);
		}
		return sub;
	}

	/**
	 * Calculates and returns the total taxes to apply to the subtotal of all
	 * MenuItems in the order. Tax rate is TAX_RATE.
	 *
	 * @return total taxes on all MenuItems
	 */
	public BigDecimal getTaxes() {

		//
		BigDecimal tx = new BigDecimal((double) 0);
		tx = TAX_RATE.multiply(this.getSubTotal());
		return tx;
	}

	/**
	 * Calculates and returns the total price of all MenuItems order, including tax.
	 *
	 * @return total price
	 */
	public BigDecimal getTotal() {

		//
		BigDecimal tot = new BigDecimal((double) 0);
		tot = tot.add(this.getTaxes()).add(this.getSubTotal());
		return tot;
	}

	/*
	 * Implements the Printable interface print method. Prints lines to a Graphics2D
	 * object using the drawString method. Prints the current contents of the Order.
	 */
	@Override
	public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
			throws PrinterException {
		int result = PAGE_EXISTS;

		if (pageIndex == 0) {
			final Graphics2D g2d = (Graphics2D) graphics;
			g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			// Now we perform our rendering
			final String[] lines = this.toString().split("\n");
			int y = 100;
			final int inc = 12;

			for (final String line : lines) {
				g2d.drawString(line, 100, y);
				y += inc;
			}
		} else {
			result = NO_SUCH_PAGE;
		}
		return result;
	}

	/**
	 * Returns a String version of a receipt for all the MenuItems in the order.
	 */
	@Override
	public String toString() {
		//
		String str = "";
		// iterator
		for (Map.Entry<MenuItem, Integer> entry : this.items.entrySet()) {
			MenuItem item = entry.getKey();
			Integer quan = entry.getValue();
			BigDecimal cost = item.getPrice().multiply(new BigDecimal((double) quan));

			str = str + String.format("%-13s %d @ $%,6.2f = $%,6.2f\n", item.getName(), quan, item.getPrice(), cost);
		}
		str = str + "\nSubtotal:                   $" + String.format("%,6.2f", this.getSubTotal()) + "\n";

		str = str + "Taxes:                      $" + String.format("%,6.2f", this.getTaxes()) + "\n";

		str = str + "Total:                      $" + String.format("%,6.2f", this.getTotal()) + "\n";
		return str;
	}

	/**
	 * Replaces the quantity of a particular MenuItem in an Order with a new
	 * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
	 * or negative, the MenuItem is removed from the Order.
	 *
	 * @param item     The MenuItem to update
	 * @param quantity The quantity to apply to item
	 */
	public void update(final MenuItem item, final int quantity) {

		//

		// remove of quan is 0
		if (quantity < 1) {
			this.items.remove(item);
		} else // updates the item by key
			this.items.put(item, quantity);

	}
}