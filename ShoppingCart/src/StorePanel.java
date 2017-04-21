import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StorePanel extends JPanel {
	private JPanel store;
	private JPanel productsPanel;
	private navPanel navPanel;
	private int storeStatus = 0;

	/**
	 * Create the panel.
	 */
	public StorePanel() {
		createStorePanel();
		createProductsPanel();
		createNavigationPanel();
	}

	/**
	 * Displays the products from the product csv file.
	 *
	 * @param products
	 *            list(table) of string arrays(rows) that contains product
	 *            info(cells)
	 */
	void viewProducts(List<String[]> products, Controller.BuyNowListener[] buyNowActionListeners) {
		int x = 0;
		if (getCurrentView().equals("Checkout") || getCurrentView().equals("Login")) {
			removeProductsFromDisplay();
			for (String[] product : products) {
				++x;
				ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2], product[3], buyNowActionListeners[x]);
				productsPanel.add(newproduct.getPanel());
			}
			store.add(productsPanel, BorderLayout.CENTER);
			storeStatus = 1;
		}
	}

	public void viewCheckout(List<String[]> accountCart) {
		if (getCurrentView().equals("Store")) {
			removeProductsFromDisplay();
			try{
				for (String[] product : accountCart) {
					ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2]);
					productsPanel.add(newproduct.getPanel());
				}
			} catch(NullPointerException n){
				return;
			}
		} else if (getCurrentView().equals("Checkout")) {
			System.out.println("You're already on the checkout page");
			return;
		}
		store.add(productsPanel, BorderLayout.CENTER);
		storeStatus = 2;
	}

	public void removeProductsFromDisplay() {
		productsPanel.removeAll();
		store.remove(productsPanel);
	}

	public boolean isProductsPanelDisplayed() {
		if (productsPanel.getParent() == null)
			return false;
		else
			return true;
	}

	public boolean isCheckoutPanelDisplayed() {
		if (productsPanel.getParent() == null)
			return false;
		else
			return true;
	}

	public JPanel getPanel() {
		return store;
	}

	public navPanel getNav() {
		return navPanel;
	}

	public void createStorePanel() {
		store = new JPanel();
		store.setLayout(new BorderLayout());
		store.setBackground(new Color(248, 247, 242));
		store.setBorder(new EmptyBorder(10, 5, 10, 5));
	}

	public void createProductsPanel() {
		productsPanel = new JPanel();
		productsPanel.setLayout(new FlowLayout(0, 10, 10));
		productsPanel.setBackground(new Color(0, 0, 0));
		productsPanel.setBounds(100, 100, 100, 100);
	}

	private void createNavigationPanel() {
		navPanel = new navPanel();
		navPanel.setLayout(new FlowLayout(0, 10, 10));
		navPanel.setBackground(new Color(0, 0, 0));
		store.add(navPanel.getPanel(), BorderLayout.NORTH);
	}

	public String getCurrentView() {
		if (storeStatus == 2)
			return "Checkout";
		else if (storeStatus == 1)
			return "Store";
		return "Login";
	}
}
