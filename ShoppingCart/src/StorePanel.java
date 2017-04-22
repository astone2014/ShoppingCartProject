import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StorePanel extends JPanel {
	private JPanel store;
	private JPanel productsPanel;
	private NavPanel NavPanel;
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
			if(product[0].equals("ID")){
				System.out.println("FIRST LINE");
				continue;
			}
			++x;
			ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2], product[3],
					product[4], product[5], product[6], buyNowListener[x]);
			productsPanel.add(newproduct.getPanel());
		}
		store.add(productsPanel, BorderLayout.CENTER);
		storeStatus = 1;
	}

	/**
	 * Displays the checkout page.
	 * 
	 * @param accountCart
	 *            The cart of the account from the model(database)
	 * @param incrementListener
	 *            adds item to cart and removes item from stock
	 * @param decrementListener
	 *            removes item from cart and adds item to stock
	 */
	public void viewCheckout(List<String[]> accountCart, Controller.IncrementListener[] incrementListener,
			Controller.DecrementListener[] decrementListener) {
		removeProductsFromDisplay();
		int x = 0;
		for (String[] product : accountCart) {
			if(product[0].equals("ID")){
				System.out.println("FIRST LINE");
				continue;
			}
			ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2], product[3], product[4], 
					incrementListener[x], decrementListener[x]);
			++x;
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
	public NavPanel getNav() {
		return NavPanel;
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
		NavPanel = new NavPanel();
		NavPanel.setLayout(new FlowLayout(0, 10, 10));
		NavPanel.setBackground(new Color(0, 0, 0));
		store.add(NavPanel.getPanel(), BorderLayout.NORTH);
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
