package javPKG;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * Executes the GUI interface for this program.
 *
 * @author Muhammad Talal Tariq
 */
public class TakeOutGUI {

	/**
	 * GUI execution.
	 *
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		String filename = "menu.txt";

		try {
			Scanner fileScanner = new Scanner(new File(filename));
			Menu menu = new Menu(fileScanner);
			fileScanner.close();
			JFrame frame = new JFrame("Foodorama");
			frame.setContentPane(new OrderPanel(menu));
			frame.setSize(280, 340);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open menu file");
		}
	}
}