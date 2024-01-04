package javPKG;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Muhammad Talal Tariq
 */
public class Cashier {

	// Attributes
	private Menu menu = null;

	/**
	 * Constructor.
	 *
	 * @param menu A Menu.
	 */
	public Cashier(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Prints the menu.
	 */
	private void printCommands() {
		System.out.println("\nMenu:");
		System.out.print(menu.toString());
		System.out.println("Press 0 when done.");
		System.out.println("Press any other key to see the menu again.\n");
	}

	/**
	 * Asks for commands and quantities. Prints a receipt when all orders have been
	 * placed.
	 *
	 * @return the completed Order.
	 */
	public Order takeOrder() {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to WLU Foodorama!");
		//
		this.printCommands();
		// System.out.println("");
		Order order = new Order();
		boolean finish = false;
		while (finish == false) {
			try {
				// menu item name
				System.out.print("Command: ");
				String inp1a = input.nextLine();
				int inp1b = Integer.valueOf(inp1a);
				if (inp1b == 0) {
					finish = true;
					System.out.println("----------------------------------------");
					System.out.println(order.toString());
					break;
				} else if (inp1b > 0 && inp1b <= this.menu.size()) {
					MenuItem men = menu.getItem(inp1b - 1);
					// item quanity
					try {
						System.out.print("How many do you want? ");
						String inp2a = input.nextLine();
						int inp2b = Integer.valueOf(inp2a);
						if (inp2b > 0) {
							order.add(men, inp2b);
						}
					} catch (NumberFormatException e) {
						System.out.println("Not a valid number");
					}
				} else {
					this.printCommands();
				}
			} catch (NumberFormatException e) {
				System.out.println("Not a valid number");
				this.printCommands();
			}
		}

		return order;
	}
}