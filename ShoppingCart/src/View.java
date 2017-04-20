import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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
		productsPanel.setBounds(200, 200, 200, 500);

		checkout = new JPanel();
		store.setBorder(new EmptyBorder(10, 5, 10, 5));
		checkoutPanel = new JPanel();
		checkoutPanel.setLayout(new FlowLayout(0, 10, 10));
		checkoutPanel.setBounds(200, 200, 200, 500);

		admin = new JPanel();
		admin.setLayout(null);

		getContentPane().add(login);
	}

	public String getUserName() {
		return userName.getText();
	}

	public char[] getUserPassword() {
		return userPassword.getPassword();
	}

	void addLoginListener(ActionListener listenerForLogin) {
		btnLogin.addActionListener(listenerForLogin);
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

	public void addCheckoutListener(ActionListener listenerForCheckout) {
		btnCheckout.addActionListener(listenerForCheckout);
	}

	public void addLogoutListener(ActionListener listenerForLogout) {
		btnLogout.addActionListener(listenerForLogout);
	}

	public void addCartAddListener(ActionListener listenerForCartAdd) {
		btnLogout.addActionListener(listenerForCartAdd);
	}

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

	void viewRegister() {
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

	void viewLogout() {
		this.removeAll();
	}

	void viewRefresh() {
		getContentPane().repaint();
		getContentPane().revalidate();
	}

	public void loginMessage(String string) {
		lblLogin.setText(string);
	}

	void viewStore() {
		viewRefresh();
	}

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

	JPanel createProductJpanel(String Name, String Price, String Count, String Description) {
		JPanel newproduct = new JPanel(new GridLayout(4, 2));
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
		newproduct.add(new JButton("Buy Now"));
		return newproduct;
	}

	JPanel createCartItemJpanel(String Name, String Price, String Count){
		JPanel newproduct = new JPanel(new GridLayout(4,2));
		newproduct.setBackground(Color.LIGHT_GRAY);
		newproduct.setBorder(new EmptyBorder(10,5,10,5));
		newproduct.add(new JLabel(Name));
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon("C:\\Users\\auste\\Downloads\\product.png"));
		newproduct.add(image);
		newproduct.add(new JLabel("$" + Price));
		newproduct.add(new JLabel("Stock: " + Count));
		JButton buynow = new JButton("Buy Now");
		buynow.addActionListener(null);
		newproduct.add(new JButton("Buy Now"));
		return newproduct;
	}

	void viewCheckout(List<String[]> products) {
		if (checkoutPanel.getParent() == null) {
			System.out.println("Parenet null");
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
}
