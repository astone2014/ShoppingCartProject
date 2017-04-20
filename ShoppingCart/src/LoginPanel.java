import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPanel extends JPanel{
	private JPanel login;
	private JLabel lblUsername = new JLabel("Username:");
	private JLabel lblPassword = new JLabel("Password:");
	private final JButton btnLogin = new JButton("Login");
	private final JButton btnLogout = new JButton("Logout");
	private final JButton btnRegister = new JButton("Register");
	private final JButton btnSignUp = new JButton("Sign Up");
	private JTextField userName = new JTextField(10);
	private JPasswordField userPassword = new JPasswordField(10);
	private final JLabel lblLogin = new JLabel("");

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
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
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(10, 42, 414, 14);
		login.add(lblLogin);
		btnRegister.setBounds(171, 174, 89, 23);
		login.add(btnRegister);
		btnLogin.setBounds(171, 143, 89, 23);
		login.add(btnLogin);
	}
	
	public JPanel getPanel(){
		return login;		
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
		} else if (AccountType == 2) {
		}
	}

	/**
	 * Displays the login page or sign up page.
	 */
	void viewLoginPage() {
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

	//Action Listeners
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
}
