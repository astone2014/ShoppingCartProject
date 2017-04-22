import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NavPanel extends JPanel {
	private JPanel NavPanel;
	private JButton btnStore;
	private JButton btnCheckout;
	private JButton btnLogout;
	private JLabel welcome;

	/**
	 * Create the panel.
	 */
	public NavPanel() {
		NavPanel = new JPanel();

		welcome = new JLabel("");
		NavPanel.add(welcome);

		btnStore = new JButton("Store");
		NavPanel.add(btnStore);

		btnCheckout = new JButton("Checkout");
		NavPanel.add(btnCheckout);

		btnLogout = new JButton("Logout");
		NavPanel.add(btnLogout);
	}

	public void addStoreListener(ActionListener listenerForStore) {
		btnStore.addActionListener(listenerForStore);
	}

	public void addCheckoutListener(ActionListener listenerForCheckout) {
		btnCheckout.addActionListener(listenerForCheckout);
	}

	public void addLogoutListener(ActionListener listenerForLogout) {
		btnLogout.addActionListener(listenerForLogout);
	}

	public JPanel getPanel() {
		return NavPanel;
	}

	public void addWelcomeMessage(String string) {
		welcome.setText(string);
		NavPanel.add(welcome);
	}

	public void removeWelcomeMessage(String string) {
		NavPanel.remove(welcome);
	}
}
