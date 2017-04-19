import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.opencsv.CSVReader;

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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//login user
				String user = userName.getText();
				char[] pass = userPassword.getPassword();
				String loginResponse = loginUser(user, pass);
				if (user.equals(loginResponse)){	// login response gives username back.
					System.out.println("Logged in to: " + loginResponse);
					login.setVisible(false);
				}else{
					System.out.println("Login failed: " + loginResponse);
				}
			}
		});
		btnLogIn.setBounds(172, 203, 89, 23);
		login.add(btnLogIn);
	}

	public String loginUser(String username, char[] password){
		CSVReader reader;
		String [] nextLine;
		List all;
		String reason = "";
		int x = -1;
		try {
			reader = new CSVReader(new FileReader("accounts.csv"));
		} catch (FileNotFoundException e1) {
			return "no csv file";
		} catch (IOException e) {
			return "io exception";
		}
		x = 0;
		return "1";
	}
}
