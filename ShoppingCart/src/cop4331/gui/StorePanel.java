package cop4331.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import cop4331.control.Controller;

public class StorePanel extends JPanel {
	private JPanel store;
	private JPanel ProductsPanel;
	private JScrollPane scrollPane;
	private NavPanel NavPanel;
	private FinancePanel FinancePanel;
	private CheckoutPanel CheckoutPanel;
	private FooterPanel FooterPanel;
	private int storeStatus = 0;

	/**
	 * Create the panel
	 */
	public StorePanel(String accountTypeString) {
		createStorePanel();
		createProductsPanel();
		createNavigationPanel(accountTypeString);
		createFooterPanel();
		scrollPane = new JScrollPane(store);
	}

	/**
	 * Displays the products from the product model
	 * @param products
	 * @param buyNowListener
	 */
	public void viewProducts(List<String[]> products, Controller.BuyNowListener[] buyNowListener) {
		int x = 0;
		removeProductsFromDisplay();
		for (String[] product : products) {
			if (product[0].equals("ID")) {
				continue;
			}
			++x;
			ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2], product[3], product[4],
					product[5], product[6], buyNowListener[x]);
			ProductsPanel.add(newproduct.getPanel());
		}
		store.add(ProductsPanel, BorderLayout.CENTER);
		storeStatus = 1;
	}

	/**
	 * Displays the admin panel by creating products and increment/decrement listeners
	 * @param products The products from the model
	 * @param stockincrementListener Stock increment listener
	 * @param stockdecrementListener Stock decrement listener
	 */
	public void viewAdmin(List<String[]> products, Controller.StockIncrementListener[] stockincrementListener,
			Controller.StockDecrementListener[] stockdecrementListener) {
		int x = 0;
		removeProductsFromDisplay();
		for (String[] product : products) {
			if (product[0].equals("ID")) {
				continue;
			}
			ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2], product[3], product[4],
					product[5], product[6], stockincrementListener[x], stockdecrementListener[x]);
			++x;
			ProductsPanel.add(newproduct.getPanel());
		}
		store.add(ProductsPanel, BorderLayout.CENTER);
		storeStatus = 4;
	}

	public void viewFinance(List<String[]> finances) {
		removeProductsFromDisplay();
		FinancePanel = new FinancePanel(finances);
		ProductsPanel.add(FinancePanel.getPanel());
		store.add(ProductsPanel, BorderLayout.CENTER);
		storeStatus = 5;
	}

	/**
	 * Displays the cart page.
	 *
	 * @param accountCart
	 *            The cart of the account from the model(database)
	 * @param incrementListener
	 *            adds item to cart and removes item from stock
	 * @param decrementListener
	 *            removes item from cart and adds item to stock
	 */
	public void viewCart(List<String[]> accountCart, Controller.IncrementListener[] incrementListener,
			Controller.DecrementListener[] decrementListener) {
		removeProductsFromDisplay();
		int x = 0;
		for (String[] product : accountCart) {
			if (product[0].equals("ID")) {
				continue;
			}
			ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2], product[3], product[4],
					product[5], incrementListener[x], decrementListener[x]);
			++x;
			ProductsPanel.add(newproduct.getPanel());
		}
		store.add(ProductsPanel, BorderLayout.CENTER);
		storeStatus = 2;
	}

	public void viewCheckout(float total) {
		removeProductsFromDisplay();
		CheckoutPanel = new CheckoutPanel(total);
		ProductsPanel.add(CheckoutPanel.getPanel());
		store.add(ProductsPanel, BorderLayout.CENTER);
		storeStatus = 3;
	}

	/**
	 * Removes all products from display
	 */
	public void removeProductsFromDisplay() {
		ProductsPanel.removeAll();
		store.remove(ProductsPanel);
	}

	/**
	 * Returns if the products panel is displayed
	 *
	 * @return true is display false if not displayed
	 */
	public boolean isProductsPanelDisplayed() {
		if (ProductsPanel.getParent() == null)
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
		if (ProductsPanel.getParent() == null)
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

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * Returns the panel of the navigation.
	 *
	 * @return nav JPanel
	 */
	public NavPanel getNav() {
		return NavPanel;
	}

	public CheckoutPanel getCheckout() {
		return CheckoutPanel;
	}

	/**
	 * Returns the footer JPanel
	 * 
	 * @return Fotter JPanel
	 */
	public FooterPanel getFooter() {
		return FooterPanel;
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
		ProductsPanel = new JPanel();
		ProductsPanel.setLayout(new WrapLayout());
		ProductsPanel.setBackground(new Color(255, 255, 255));
		ProductsPanel.setBounds(100, 100, 100, 100);
	}

	/**
	 * Create Navigation
	 * 
	 * @param accountTypeString
	 */
	private void createNavigationPanel(String accountTypeString) {
		if (accountTypeString.equals("Admin")) {
			NavPanel = new NavPanel("Admin");
			NavPanel.setLayout(new FlowLayout(0, 10, 10));
			NavPanel.setBackground(new Color(0, 0, 0));
			store.add(NavPanel.getPanel(), BorderLayout.NORTH);
		} else {
			NavPanel = new NavPanel("User");
			NavPanel.setLayout(new FlowLayout(0, 10, 10));
			NavPanel.setBackground(new Color(0, 0, 0));
			store.add(NavPanel.getPanel(), BorderLayout.NORTH);
		}
	}

	/**
	 * Creates the footer panel
	 */
	private void createFooterPanel() {
		FooterPanel = new FooterPanel();
		FooterPanel.setLayout(new FlowLayout(0, 10, 10));
		FooterPanel.setBackground(new Color(0, 0, 0));
		store.add(FooterPanel.getPanel(), BorderLayout.SOUTH);
	}

	/**
	 * Returns the current store page
	 *
	 * @return displayed store page
	 */
	public String getCurrentView() {
		if (storeStatus == 5)
			return "Finance";
		else if (storeStatus == 4)
			return "Admin";
		else if (storeStatus == 3)
			return "Checkout";
		else if (storeStatus == 2)
			return "Cart";
		else if (storeStatus == 1)
			return "Store";
		return "Login";
	}
}
