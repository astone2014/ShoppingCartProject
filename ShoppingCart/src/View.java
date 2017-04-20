import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class View extends JFrame {
	private JPanel login;
	private JPanel store;
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

	public View(){
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
		productsPanel = new JPanel();
		productsPanel.setLayout(new GridLayout(4,4));

		admin = new JPanel();
		admin.setLayout(null);
		
		getContentPane().add(login);
	}
	
	public String getUserName(){
		return userName.getText();
	}
	
	public char[] getUserPassword(){
		return userPassword.getPassword();
	}
	
	void addLoginListener(ActionListener listenerForLogin){
		btnLogin.addActionListener(listenerForLogin);
	}
	void addRegisterListener(ActionListener listenerForRegister){
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
	
	void viewLogedIn(int AccountType){
		if(AccountType == 1){
			getContentPane().remove(login);
			getContentPane().add(store);
			store.add(btnStore);
			store.add(btnCheckout);
			store.add(btnLogout);
			viewRefresh();
		} else if(AccountType == 2){
			getContentPane().remove(login);
			getContentPane().add(admin);
			viewRefresh();
		}
	}
	
	void viewRegister(){
		getContentPane().remove(store);
		getContentPane().add(login);
		if(btnStore.getParent() != null){				// if at store
			store.remove(btnStore);
			login.remove(btnLogin);
			login.add(btnLogin);
		}else if(btnSignUp.getParent() == null){		// if at login
			login.remove(btnLogin);
			btnRegister.setText("Back");
			btnSignUp.setBounds(171, 143, 89, 23);
			login.add(btnSignUp);
		}else{											// if at registration
			login.add(btnLogin);
			btnRegister.setText("Register");
			login.remove(btnSignUp);
		}
		lblLogin.setText("");
		viewRefresh();
	}
	
	void viewLogout(){
		this.removeAll();
	}
	
	void viewRefresh(){
		getContentPane().repaint();
		getContentPane().revalidate();
	}

	public void loginMessage(String string) {
		lblLogin.setText(string);
	}
	
	void viewStore(){
		viewRefresh();
	}
	
	void viewProducts(List<String[]> products){
		for(String[] product : products){
			JPanel newproduct = createProductJpanel(product[0], product[1], product[2], product[3]);
			productsPanel.add(newproduct);
			for(int i=0;i<4;i++){
				System.out.println(product[i]);
			}
			store.add(productsPanel);
			viewRefresh();
			System.out.println("");
		}
	}
	
	JPanel createProductJpanel(String Name, String Price, String Count, String Description){
		JPanel newproduct = new JPanel();
		newproduct.add(new JLabel(Name));
		newproduct.add(new JLabel(Price));
		newproduct.add(new JLabel(Count));
		newproduct.add(new JLabel(Description));
		return newproduct;
	}
}
