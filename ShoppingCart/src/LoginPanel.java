import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPanel extends JPanel {
	private JPanel login;
	private JLabel lblUsername = new JLabel("Username:");
	private JLabel lblPassword = new JLabel("Password:");
	private final JButton btnLogin = new JButton("Login");
	private final JButton btnRegister = new JButton("Register");
	private final JButton btnSignUp = new JButton("Sign Up");
	private JTextField userName = new JTextField(10);
	private JPasswordField userPassword = new JPasswordField(10);
	private final JLabel lblLogin = new JLabel("");
	private final JPanel buttonPanel = new JPanel();
	private final JPanel usernamePanel = new JPanel();
	private final JPanel passwordPanel = new JPanel();

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		login = new JPanel();
		addLoginComponents();
		login.setBackground(new Color(248, 247, 242));

		login.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(btnLogin);
		buttonPanel.add(btnRegister);
	}

	/**
	 * Adds login components to the login panel
	 */
	private void addLoginComponents() {
		createUserNameInput();
		createPasswordInput();
		createLoginMessageLabel();
		createRegisterButton();
	}

	public void viewRegistration() {
		removeRegisterButton();
		createSignUpButton();
	}

	public void viewLogin() {
		removeSignUpButton();
		lblLogin.setText("");
		createRegisterButton();
	}

	/**
	 * Creates user input
	 */
	public void createUserNameInput() {
		login.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		login.add(usernamePanel);
		usernamePanel.add(lblUsername);
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		usernamePanel.add(userName);
	}

	/**
	 * Creates password input
	 */
	public void createPasswordInput() {

		login.add(passwordPanel);
		passwordPanel.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		passwordPanel.add(userPassword);
	}

	/**
	 * Creates login message label
	 */
	public void createLoginMessageLabel() {
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		login.add(lblLogin);
	}

	/**
	 * Creates a registration button
	 */
	public void createRegisterButton() {
		buttonPanel.add(btnLogin);
		buttonPanel.add(btnRegister);
	}

	public void removeRegisterButton() {
		buttonPanel.remove(btnLogin);
		buttonPanel.remove(btnRegister);
	}

	public void createSignUpButton() {
		buttonPanel.add(btnSignUp);
		buttonPanel.add(btnLogin);
	}

	public void removeSignUpButton() {
		buttonPanel.remove(btnSignUp);
		buttonPanel.remove(btnLogin);
	}

	/**
	 * Returns the login panel
	 *
	 * @return login panel
	 */
	public JPanel getPanel() {
		return login;
	}

	/**
	 * Returns true is the login panel is displayed otherwise false
	 *
	 * @return if login is displayed
	 */
	public boolean isLoginPanelDisplayed() {
		if (login.getParent() == null)
			return false;
		else
			return true;
	}

	/**
	 * false if the signup button is showing true if it is not showing
	 * 
	 * @return
	 */
	public boolean getLoginStatus() {
		if (btnSignUp.getParent() == null)
			return true;
		else
			return false;
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

	// Action Listeners
	void addLoginListener(ActionListener listenerForLogin) {
		btnLogin.addActionListener(listenerForLogin);
	}

	void addRegisterListener(ActionListener listenerForRegister) {
		btnRegister.addActionListener(listenerForRegister);
	}

	public void addSignUpListener(ActionListener listenerForSignUp) {
		btnSignUp.addActionListener(listenerForSignUp);
	}

}
