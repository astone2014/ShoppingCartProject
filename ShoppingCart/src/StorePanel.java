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
	 * Create the panel
	 */
	public StorePanel() {
		createStorePanel();
		createProductsPanel();
		createNavigationPanel();
	}

	/**
	 * Displays the products from the product model
	 *
	 * @param products
	 *            list(table) of string arrays(rows) that contains product
	 *            info(cells)
	 */
	void viewProducts(List<String[]> products, Controller.BuyNowListener[] buyNowListener) {
		int x = 0;
		removeProductsFromDisplay();
		for (String[] product : products) {
			++x;
			ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2], product[3],
					buyNowListener[x]);
			productsPanel.add(newproduct.getPanel());
		}
		store.add(productsPanel, BorderLayout.CENTER);
		storeStatus = 1;
	}

	/**
	 * Displays checkout page
	 *
	 * @param accountCart
	 *            List<String[]> from model
	 */
	public void viewCheckout(List<String[]> accountCart, Controller.IncrementListener[] incrementListener,
		Controller.DecrementListener[] decrementListener) {
		removeProductsFromDisplay();
		int x = 0;
		for (String[] product : accountCart) {
			++x;
			ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2], incrementListener[x],
					decrementListener[x]);
			productsPanel.add(newproduct.getPanel());
		}
		store.add(productsPanel, BorderLayout.CENTER);
		storeStatus = 2;
	}

	/**
	 * Removes all products from display
	 */
	public void removeProductsFromDisplay() {
		productsPanel.removeAll();
		store.remove(productsPanel);
	}

	/**
	 * Returns if the products panel is displayed
	 *
	 * @return true is display false if not displayed
	 */
	public boolean isProductsPanelDisplayed() {
		if (productsPanel.getParent() == null)
			return false;
		else
			return true;
	}

	/**
	 * Returns if the checkout panel is displayed
	 *
	 * @return true is display false if not displayed
	 */
	public boolean isCheckoutPanelDisplayed() {
		if (productsPanel.getParent() == null)
			return false;
		else
			return true;
	}

	/**
	 * Returns the panel of the store.
	 *
	 * @return Store JPanel
	 */
	public JPanel getPanel() {
		return store;
	}

	/**
	 * Returns the panel of the navigation.
	 *
	 * @return nav JPanel
	 */
	public navPanel getNav() {
		return navPanel;
	}

	/**
	 * Creates the store panel
	 */
	public void createStorePanel() {
		store = new JPanel();
		store.setLayout(new BorderLayout());
		store.setBorder(new EmptyBorder(10, 5, 10, 5));
	}

	/**
	 * Creates the products panel.
	 */
	public void createProductsPanel() {
		productsPanel = new JPanel();
		productsPanel.setLayout(new FlowLayout(0, 10, 10));
		productsPanel.setBackground(new Color(255, 255, 255));
		productsPanel.setBounds(100, 100, 100, 100);
	}

	/**
	 * Creates the navigation panel
	 */
	private void createNavigationPanel() {
		navPanel = new navPanel();
		navPanel.setLayout(new FlowLayout(0, 10, 10));
		navPanel.setBackground(new Color(0, 0, 0));
		store.add(navPanel.getPanel(), BorderLayout.NORTH);
	}

	/**
	 * Returns the current store page
	 *
	 * @return displayed store page
	 */
	public String getCurrentView() {
		if (storeStatus == 2)
			return "Checkout";
		else if (storeStatus == 1)
			return "Store";
		return "Login";
	}
}
