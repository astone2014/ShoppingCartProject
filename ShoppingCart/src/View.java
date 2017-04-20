import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Shopping Cart Project Group 19
 * 
 * @author Austen Stone, Reshma Thomas, Arian Tirado
 *
 */
public class View extends JFrame {
	private JPanel login;
	private JPanel store;
	private JPanel checkout;
	private JPanel checkoutPanel;
	private JPanel productsPanel;
	private JPanel admin;
	private JTextField userName = new JTextField(10);
	private JPasswordField userPassword = new JPasswordField(10);
	private JLabel lblUsername = new JLabel("Username:");
	private JLabel lblPassword = new JLabel("Password:");
	private final JButton btnLogin = new JButton("Login");
	private final JButton btnLogout = new JButton("Logout");
	private final JButton btnRegister = new JButton("Register");
	private final JButton btnSignUp = new JButton("Sign Up");
	private final JButton btnStore = new JButton("Store");
	private final JButton btnCheckout = new JButton("Checkout");
	private final JLabel lblLogin = new JLabel("");

	/**
	 * Constructor for view
	 */
	public View() {
		login = new JPanel();
		login.setLayout(null);
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setBounds(10, 85, 151, 14);
		login.add(lblUsername);
		userName.setBounds(171, 82, 90, 20);
		login.add(userName);
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setBounds(10, 115, 149, 14);
		login.add(lblPassword);
		userPassword.setBounds(171, 112, 90, 20);
		login.add(userPassword);
		btnLogin.setBounds(171, 143, 89, 23);
		login.add(btnLogin);
		btnRegister.setBounds(171, 174, 89, 23);
		login.add(btnRegister);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(10, 42, 414, 14);
		login.add(lblLogin);

		store = new JPanel();
		store.setBorder(new EmptyBorder(10, 5, 10, 5));
		productsPanel = new JPanel();
		productsPanel.setLayout(new FlowLayout(0, 10, 10));
		productsPanel.setBounds(100, 100, 100, 100);

		checkout = new JPanel();
		store.setBorder(new EmptyBorder(10, 5, 10, 5));
		checkoutPanel = new JPanel();
		checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		checkoutPanel.setBounds(100, 76, 75, 75);

		admin = new JPanel();
		admin.setLayout(null);

		getContentPane().add(login);

	}

	/**
	 * Displays the front end of the store or admin page depending on account
	 * type
	 *
	 * @param AccountType
	 *            The type of the account {1:user, 2:admin}
	 */
	void viewLogedIn(int AccountType) {
		if (AccountType == 1) {
			getContentPane().remove(login);
			getContentPane().add(store);
			store.add(btnStore);
			store.add(btnCheckout);
			store.add(btnLogout);
			viewRefresh();
		} else if (AccountType == 2) {
			getContentPane().remove(login);
			getContentPane().add(admin);
			viewRefresh();
		}
	}

	/**
	 * Displays the login page or sign up page.
	 */
	void viewLoginPage() {
		getContentPane().remove(store);
		getContentPane().add(login);
		if (btnStore.getParent() != null) { // if at store
			store.remove(btnStore);
			login.remove(btnLogin);
			login.add(btnLogin);
		} else if (btnSignUp.getParent() == null) { // if at login
			login.remove(btnLogin);
			btnRegister.setText("Back");
			btnSignUp.setBounds(171, 143, 89, 23);
			login.add(btnSignUp);
		} else { // if at registration
			login.add(btnLogin);
			btnRegister.setText("Register");
			login.remove(btnSignUp);
		}
		lblLogin.setText("");
		viewRefresh();
	}

	/**
	 * Scans the username field for a username
	 *
	 * @return username from usernamefield
	 */
	public String getUserName() {
		return userName.getText();
	}

	/**
	 * Scans the password field for a password
	 *
	 * @return password from password field
	 */
	public char[] getUserPassword() {
		return userPassword.getPassword();
	}

	/**
	 * Changes login label to inform user of login changes.
	 *
	 * @param string
	 *            the login message
	 */
	public void loginMessage(String string) {
		lblLogin.setText(string);
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
				JPanel newproduct = createProductJpanel(product[0], product[1], product[2], product[3]);
				productsPanel.add(newproduct);
			}
			checkoutPanel.removeAll();
			store.remove(checkoutPanel);
			store.add(productsPanel);
			viewRefresh();
		}
	}

	/**
	 * Creates a panel for the product
	 *
	 * @param Name
	 *            name of product
	 * @param Price
	 *            price of product
	 * @param Count
	 *            how many of the products are in stock
	 * @param Description
	 *            a short description of the product
	 * @return
	 */
	JPanel createProductJpanel(String Name, String Price, String Count, String Description) {
		JPanel newproduct = new JPanel(new GridLayout(4, 2));
		newproduct.setName(Name);
		newproduct.setBackground(Color.LIGHT_GRAY);
		newproduct.setBorder(new EmptyBorder(10, 5, 10, 5));
		newproduct.add(new JLabel(Name));
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon("C:\\Users\\auste\\Downloads\\product.png"));
		newproduct.add(image);
		newproduct.add(new JLabel("$" + Price));
		newproduct.add(new JLabel("Stock: " + Count));
		JLabel description = new JLabel(Description);
		description.setBounds(100, 100, 100, 100);
		newproduct.add(description);
		JButton buynow = new JButton("Buy Now");
		newproduct.add(buynow);
		buynow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked " + newproduct.getName());
			}
		});
		return newproduct;
	}

	/**
	 * displays checkout page and reload data.
	 *
	 * @param products
	 */
	void viewCheckout(List<String[]> products) {
		if (checkoutPanel.getParent() == null) {
			for (String[] product : products) {
				JPanel newCartItem = createCartItemJpanel(product[0], product[1], product[2]);
				checkoutPanel.add(newCartItem);
			}
			productsPanel.removeAll();
			store.remove(productsPanel);
			store.add(checkoutPanel);
			viewRefresh();
		}
	}
	
	/**
	 * Creates a JPanel for the cart item
	 *
	 * @param Name
	 *            name of item
	 * @param Price
	 *            price of item
	 * @param Count
	 *            count of item
	 * @return
	 */
	JPanel createCartItemJpanel(String Name, String Price, String Count) {
		JPanel newproduct = new JPanel(new GridLayout(4, 2));
		newproduct.setBackground(Color.LIGHT_GRAY);
		newproduct.add(new JLabel(Name));
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon("C:\\Users\\auste\\Downloads\\product.png"));
		newproduct.add(image);
		newproduct.add(new JLabel("$" + Price));
		newproduct.add(new JLabel("Stock: " + Count));
		return newproduct;
	}

	/**
	 * Repaints and revalidates the content pane to display changes.
	 */
	void viewRefresh() {
		getContentPane().repaint();
		getContentPane().revalidate();
	}
	
	public void addCheckoutListener(ActionListener listenerForCheckout) {
		btnCheckout.addActionListener(listenerForCheckout);
	}

	void addLoginListener(ActionListener listenerForLogin) {
		btnLogin.addActionListener(listenerForLogin);
	}

	public void addLogoutListener(ActionListener listenerForLogout) {
		btnLogout.addActionListener(listenerForLogout);
	}

	void addRegisterListener(ActionListener listenerForRegister) {
		btnRegister.addActionListener(listenerForRegister);
	}

	public void addSignUpListener(ActionListener listenerForSignUp) {
		btnSignUp.addActionListener(listenerForSignUp);
	}

	public void addStoreListener(ActionListener listenerForStore) {
		btnStore.addActionListener(listenerForStore);
	}
	
	public void addBuyNowListener(ActionListener buyNowListenerm) {
	}

	public Object getBuyNowName() {
		// TODO Auto-generated method stub
		return null;
	}
}
