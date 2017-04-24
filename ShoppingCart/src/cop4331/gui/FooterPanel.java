package cop4331.gui;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cop4331.control.Controller;

public class FooterPanel extends JPanel {
	private static JPanel FooterPanel;
	private static JButton btnCheckout;
	private static JButton btnCompleteTransaction;
	private static JLabel carttotal;

	/**
	 * Create the panel.
	 */
	public FooterPanel() {
		FooterPanel = new JPanel();

		carttotal = new JLabel("");
		FooterPanel.add(carttotal);

		btnCheckout = new JButton("Checkout");
		FooterPanel.add(btnCheckout);

		btnCompleteTransaction = new JButton("Complete Purchase");
	}

	/**
	 * Adds a listener for the checkout button
	 * 
	 * @param listener
	 *            Checkout listener from controller
	 */
	public void addCheckoutListener(Controller.CheckoutListener listener) {
		btnCheckout.addActionListener(listener);
	}

	/**
	 * Adds a listener for the complete purchase button
	 * 
	 * @param listener
	 *            CompleteTransaction listener from controller
	 */
	public void completeTransactionListener(Controller.CompleteTransactionListener listner) {
		btnCompleteTransaction.addActionListener(listner);
	}

	/**
	 * Adds the complete transaction to the panel
	 * 
	 * @param completeTransactionListener
	 *            CompleteTransaction listener from the controller
	 */
	public static void addCompleteTransactionBtn(Controller.CompleteTransactionListener completeTransactionListener) {
		FooterPanel.add(btnCompleteTransaction);
	}

	/**
	 * Removes the complete transaction to the panel
	 */
	public void removeCompleteTransactionBtn() {
		FooterPanel.remove(btnCompleteTransaction);
	}

	/**
	 * Adds the checkout button the the footer panel
	 */
	public static void addCheckoutBtn() {
		FooterPanel.add(btnCheckout);
		FooterPanel.remove(btnCompleteTransaction);
	}

	/**
	 * Removes the checkout button the the footer panel
	 */
	public void removeCheckoutBtn() {
		FooterPanel.remove(btnCheckout);
		FooterPanel.add(btnCompleteTransaction);
	}

	/**
	 * Returns the footer JPanel
	 * 
	 * @return Footer JPanel
	 */
	public JPanel getPanel() {
		return FooterPanel;
	}

	/**
	 * Sets the total text of the footer
	 * 
	 * @param cartTotal
	 *            Total cost of all items in the cart
	 */
	public static void setTotalText(float cartTotal) {
		carttotal.setText("TOTAL: " + cartTotal);
	}
}
