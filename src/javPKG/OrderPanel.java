package javPKG;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author Muhammad Talal Tariq
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

	// Attributes
	private Menu menu = null; // Menu attached to panel.
	private final Order order = new Order(); // Order to be printed by panel.
	// GUI Widgets
	private final JButton printButton = new JButton("Print");
	private final JLabel subtotalLabel = new JLabel("0");
	private final JLabel taxLabel = new JLabel("0");
	private final JLabel totalLabel = new JLabel("0");

	private JLabel nameLabels[] = null;
	private JLabel priceLabels[] = null;
	// TextFields for menu item quantities.
	private JTextField quantityFields[] = null;

	/**
	 * Displays the menu in a GUI.
	 *
	 * @param menu The menu to display.
	 */
	public OrderPanel(final Menu menu) {
		this.menu = menu;
		this.nameLabels = new JLabel[this.menu.size()];
		this.priceLabels = new JLabel[this.menu.size()];
		this.quantityFields = new JTextField[this.menu.size()];
		this.layoutView();
		this.registerListeners();
	}

	/**
	 * Implements an ActionListener for the 'Print' button. Prints the current
	 * contents of the Order to a system printer or PDF.
	 */
	private class PrintListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {

			//
			final PrinterJob pj = PrinterJob.getPrinterJob();
			pj.setPrintable(order);

			if (pj.printDialog()) {
				try {
					pj.print();
				} catch (final Exception printException) {
					// System.out.println("Printer error");
					System.err.println(printException);

				}
			}
		}
	}

	/**
	 * Implements a FocusListener on a JTextField. Accepts only positive integers,
	 * all other values are reset to 0. Adds a new MenuItem to the Order with the
	 * new quantity, updates an existing MenuItem in the Order with the new
	 * quantity, or removes the MenuItem from the Order if the resulting quantity is
	 * 0.
	 */
	private class QuantityListener implements FocusListener {
		private MenuItem item = null;

		QuantityListener(final MenuItem item) {
			this.item = item;
		}

		//
		@Override
		public void focusGained(final FocusEvent evt) {
			JTextField input = (JTextField) evt.getSource();
			input.setText("");
		}

		// update stuff when user clicks out of box
		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub

			JTextField input = (JTextField) e.getSource();
			String numstr = input.getText();

			if (numstr.isBlank()) {
				input.setText("0");
			} else {
				int num = 0;
				try {
					num = Integer.valueOf(numstr);
				} catch (NumberFormatException evt) {
					input.setText("0");
					// System.out.println("Not a valid number");
				}

				if (num > 0) {
					order.update(this.item, num);
				} else {
					order.update(this.item, 0);
					input.setText("0");
				}
				// updating totals
				subtotalLabel.setText(String.format("$%.2f", order.getSubTotal()));
				taxLabel.setText(String.format("$%.2f", order.getTaxes()));
				totalLabel.setText(String.format("$%.2f", order.getTotal()));

			}
		}
	}

	/**
	 * Layout the panel.
	 */
	private void layoutView() {
		//
		nameLabels = new JLabel[this.menu.size()];
		nameLabels = new JLabel[this.menu.size()];

		// rows, cols, horiz gap, vertical gap
		setLayout(new GridLayout(5 + this.menu.size(), 3, 10, 6));
		// headers
		add(new JLabel("Item"));
		add(new JLabel("Price"));
		add(new JLabel("Quantity"));
		// menu item rows
		for (int i = 0; i < menu.size(); i++) {
			JLabel name = new JLabel(this.menu.getItem(i).getName());
			nameLabels[i] = name;
			add(name);
			JLabel price = new JLabel(this.menu.getItem(i).getPrice().toString());
			priceLabels[i] = price;
			add(price);
			JTextField quantityField = new JTextField("0");
			quantityFields[i] = quantityField;
			quantityField.addFocusListener(new QuantityListener(this.menu.getItem(i)));
			add(quantityField);

		}
		// receipt rows
		add(new JLabel("Subtotal:"));
		add(new JLabel()); // placeholder
		add(subtotalLabel);
		add(new JLabel("Tax:"));
		add(new JLabel()); // placeholder
		add(taxLabel);
		add(new JLabel("Total:"));
		add(new JLabel()); // placeholder
		add(totalLabel);
		// print button row
		add(new JLabel()); // placeholder
		add(printButton);
		add(new JLabel()); // placeholder

	}

	/**
	 * Register the widget listeners.
	 */
	private void registerListeners() {
		// Register the PrinterListener with the print button.
		this.printButton.addActionListener(new PrintListener());

		//
	}

}