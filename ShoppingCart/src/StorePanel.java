import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StorePanel extends JPanel {
	private JPanel store;
	private JPanel productsPanel;
	private JPanel navPanel;
	private JButton btnStore;
	/**
	 * Create the panel.
	 */
	public StorePanel() {
		store = new JPanel();
		store.setBorder(new EmptyBorder(10, 5, 10, 5));
		productsPanel = new JPanel();
		navPanel = new JPanel();
		productsPanel.setLayout(new FlowLayout(0, 10, 10));
		productsPanel.setBounds(100, 100, 100, 100);
		JButton btnStore_1 = new JButton("Store");
		navPanel.add(btnStore_1);
		JButton btnCheckout = new JButton("Checkout");
		navPanel.add(btnCheckout);
		store.add(navPanel);
	}
	/**
	 * Displays the products from the product csv file.
	 *
	 * @param products
	 *            list(table) of string arrays(rows) that contains product
	 *            info(cells)
	 */
	void viewProducts(List<String[]> products) {
		if (productsPanel.getParent() == null) {
			for (String[] product : products) {
				ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2], product[3]);
				productsPanel.add(newproduct.getPanel());
			}
		}
		store.add(productsPanel);
	}

	public void addStoreListener(ActionListener listenerForStore) {
		btnStore.addActionListener(listenerForStore);
	}
	
	public void addBuyNowListener(ActionListener buyNowListenerm) {
	}

	public JPanel getPanel() {
		return store;
	}
}
