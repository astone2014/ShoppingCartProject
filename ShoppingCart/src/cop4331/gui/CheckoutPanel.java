package cop4331.gui;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckoutPanel extends JPanel {
	private JPanel CheckoutPanel;
	private static JTextField FirstName;
	private static JTextField LastName;
	private static JTextField Email;
	private static JTextField Address;
	private static JLabel carttotal;
	private static JTextField cc;

	/**
	 * Create the checkout panel.
	 *
	 * @param total
	 *            Cart Total
	 */
	public CheckoutPanel(float total) {
		CheckoutPanel = new JPanel(new WrapLayout());
		
		CheckoutPanel.setPreferredSize(new Dimension(125, 300));
		FirstName = new JTextField();

		CheckoutPanel.add(new JLabel("TOTAL: $" + total));
		CheckoutPanel.add(new JLabel("First Name: "));
		FirstName = new JTextField();
		CheckoutPanel.add(FirstName);
		FirstName.setColumns(10);

		CheckoutPanel.add(new JLabel("Last Name: "));
		LastName = new JTextField();
		CheckoutPanel.add(LastName);
		LastName.setColumns(10);

		CheckoutPanel.add(new JLabel("Email: "));
		Email = new JTextField();
		CheckoutPanel.add(Email);
		Email.setColumns(10);

		CheckoutPanel.add(new JLabel("Address: "));
		Address = new JTextField();
		CheckoutPanel.add(Address);
		Address.setColumns(10);

		CheckoutPanel.add(new JLabel("CC: "));
		cc = new JTextField();
		cc.setColumns(10);
		cc.setText("0000 1111 2222 3333 4444");

		CheckoutPanel.add(cc);

		carttotal = new JLabel("");
		CheckoutPanel.add(carttotal);
	}

	/**
	 * Returns the checkoutpanel
	 *
	 * @return Checkoutpanel
	 */
	public JPanel getPanel() {
		return CheckoutPanel;
	}

	/**
	 * Returns the first name from the checkout panel's text field.
	 *
	 * @return Entered first name
	 */
	public static String getFirstName() {
		try {
			return FirstName.getText();
		} catch (NullPointerException n) {
			return "null";
		}
	}

	/**
	 * Returns the last name from the checkout panel's text field.
	 *
	 * @return Entered last name
	 */
	public static String getLastName() {
		try {
			return LastName.getText();
		} catch (NullPointerException n) {
			return "null";
		}
	}

	/**
	 * Returns the email address from the checkout panel's text field.
	 *
	 * @return email address
	 */
	public static String getEmail() {
		try {
			return Email.getText();
		} catch (NullPointerException n) {
			return "null";
		}
	}

	/**
	 * Returns the address from the checkout panel's text field.
	 *
	 * @return address
	 */
	public static String getAddress() {
		try {
			return Address.getText();
		} catch (NullPointerException n) {
			return "null";
		}
	}

	/**
	 * Sets the cart total value on the checkout panel.
	 *
	 * @param cartTotal
	 *            User cart total
	 */
	public static void setTotalText(float cartTotal) {
		carttotal.setText("TOTAL: " + cartTotal);
	}

	/**
	 * Returns the credit card from the checkout panel's text field.
	 *
	 * @return credit card
	 */
	public static String getCreditCard() {
		try {
			return cc.getText();
		} catch (NullPointerException n) {
			return "null";
		}
	}
}
