import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FooterPanel extends JPanel {
	private JPanel FooterPanel;
	private JButton btnCheckout;
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
		
	}

	public void addCheckoutListener(Controller.CheckoutListener listenerForStore) {
		btnCheckout.addActionListener(listenerForStore);
	}

	public JPanel getPanel() {
		return FooterPanel;
	}

	public static void setTotalText(float cartTotal) {
		carttotal.setText("TOTAL: " + cartTotal);
	}
}
