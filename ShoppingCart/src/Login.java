 import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JPanel {
	
	private JTextField userName;
	private JPasswordField userPassword;
	private JPanel login;

	/**
	 * Create the panel.
	 */
	public Login() {
		
		login = this;
		login.setLayout(null);
		
		userName = new JTextField();
		userName.setBounds(171, 112, 90, 20);
		add(userName);
		userName.setColumns(10);
		
		userPassword = new JPasswordField();
		userPassword.setBounds(171, 142, 90, 20);
		login.add(userPassword);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(72, 115, 68, 14);
		login.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(72, 145, 63, 14);
		login.add(lblPassword);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// verify passwrod and username
				String name = userName.getText();
				System.out.println(name);
			}
		});
		btnLogIn.setBounds(172, 203, 89, 23);
		login.add(btnLogIn);
		
	}
}
