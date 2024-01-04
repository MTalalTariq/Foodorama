package javPKG;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Executes the text-based interface for this program.
 *
 * @author Muhammad Talal Tariq
 */
public class TakeOutText {

	/**
	 * Test-based execution.
	 *
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		String filename = "menu.txt";

		try {
			Scanner fileScanner = new Scanner(new File(filename));
			Menu menu = new Menu(fileScanner);
			fileScanner.close();
			Cashier cashier = new Cashier(menu);
			cashier.takeOrder();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open menu file");
		}
		System.exit(0);
	}
}