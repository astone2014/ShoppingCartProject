import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Register extends JPanel {
	private JTextField newuserName;
	private JPasswordField newuserPassword;
	private JPanel register;

	/**
	 * Create the panel.
	 */
	public Register() {
		register = this;
		register.setLayout(null);

		newuserName = new JTextField();
		newuserName.setBounds(171, 112, 90, 20);
		add(newuserName);
		newuserName.setColumns(10);

		newuserPassword = new JPasswordField();
		newuserPassword.setBounds(171, 142, 90, 20);
		register.add(newuserPassword);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(72, 115, 68, 14);
		register.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(72, 145, 63, 14);
		register.add(lblPassword);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked Register");
				JLabel lblRegisteredSuccessfully = new JLabel("Registered Successfully");
				lblRegisteredSuccessfully.setBounds(165, 237, 113, 14);
				add(lblRegisteredSuccessfully);
			}

			private String addUser(String user, char[] pass) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		btnRegister.setBounds(172, 203, 89, 23);
		register.add(btnRegister);
	}
}
