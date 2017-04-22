import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckoutPanel extends JPanel {
	private JPanel NavPanel;
	private JButton btnCheckout;
	private static JLabel carttotal;

	/**
	 * Create the panel.
	 */
	public CheckoutPanel() {
		NavPanel = new JPanel();

		carttotal = new JLabel("");
		NavPanel.add(carttotal);

		btnCheckout = new JButton("Checkout");
		NavPanel.add(btnCheckout);
	}

	public void addCheckoutListener(Controller.CheckoutListener listenerForStore) {
		btnCheckout.addActionListener(listenerForStore);
	}

	public JPanel getPanel() {
		return NavPanel;
	}

	public static void setTotalText(float cartTotal) {
		carttotal.setText("TOTAL: " + cartTotal);
	}
}
