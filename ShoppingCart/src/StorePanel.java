import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StorePanel extends JPanel {
	private JPanel store;
	private JPanel productsPanel;
	private NavPanel NavPanel;
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
	 * Displays the products from the product model
	 *
	 * @param products
	 *            list(table) of string arrays(rows) that contains product
	 *            info(cells)
	 */
	void viewAdmin(List<String[]> products, Controller.StockIncrementListener[] stockincrementListener,
			Controller.StockDecrementListener[] stockdecrementListener) {
		int x = 0;
		removeProductsFromDisplay();
		for (String[] product : products) {
			if(product[0].equals("ID")){
				continue;
			}
			ProductPanel newproduct = new ProductPanel(product[0], product[1], product[2], product[3],
					product[4], product[5], product[6], stockincrementListener[x], stockdecrementListener[x]);
			++x;
			productsPanel.add(newproduct.getPanel());
		}
		store.add(productsPanel, BorderLayout.CENTER);
		storeStatus = 4;
	}
	
	void viewFinance(List<String[]> finances) {
		removeProductsFromDisplay();
		for (String[] sale : finances) {
			if(sale[0].equals("ID")){
				continue;
			}
			FinancePanel financepanel = new FinancePanel(sale[0], sale[1], sale[2], sale[3],
					sale[4], sale[5], sale[6]);
			productsPanel.add(financepanel.getPanel());
		}
		store.add(productsPanel, BorderLayout.CENTER);
		storeStatus = 4;
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
			if(product[0].equals("ID")){
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
	
	public void viewCheckout(float total) {
		removeProductsFromDisplay();
		store.add(productsPanel, BorderLayout.CENTER);
		productsPanel.add(new JLabel("TOTAL: $" + total));
		String[] requiredFields = {"First Name: ", "Last Name: ", "Email: ", "Address: "};
		for(String field: requiredFields){
			productsPanel.add(new JLabel(field));
			JTextField newfield = new JTextField();
			productsPanel.add(newfield);
			newfield.setColumns(10);
		}
		productsPanel.add(new JLabel("CC: "));
		JTextField cc = new JTextField();
		cc.setColumns(10);
		cc.setText("0000 1111 2222 3333 4444");
		productsPanel.add(cc);
		storeStatus = 3;
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
		productsPanel = new JPanel();
		productsPanel.setLayout(new FlowLayout(0, 10, 10));
		productsPanel.setBackground(new Color(255, 255, 255));
		productsPanel.setBounds(100, 100, 100, 100);
	}

/**
 * Create Navigation
 * @param accountTypeString
 */
	private void createNavigationPanel(String accountTypeString) {
		if(accountTypeString.equals("Admin")){
			NavPanel = new NavPanel("Admin");
			NavPanel.setLayout(new FlowLayout(0, 10, 10));
			NavPanel.setBackground(new Color(0, 0, 0));
			store.add(NavPanel.getPanel(), BorderLayout.NORTH);
		}else{
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
		if (storeStatus == 4)
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
