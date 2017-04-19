import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
		setLayout(null);

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

		JLabel lblSuccess = new JLabel("Success");
		lblSuccess.setBounds(10, 47, 430, 14);
		add(lblSuccess);
		lblSuccess.setVisible(false);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addUser(newuserName.getText(), newuserPassword.getPassword());
				String registerResponse = addUser(newuserName.getText(), newuserPassword.getPassword());
				if (registerResponse.equals(newuserName.getText())){
					lblSuccess.setText("New User Created: " + registerResponse);
				}else{
					lblSuccess.setText("ERROR: " + registerResponse);
				}
				lblSuccess.setVisible(true);
				register.revalidate();
				register.repaint();
			}
		});
		btnRegister.setBounds(172, 203, 89, 23);
		register.add(btnRegister);
	}
	
	private String addUser(String user, char[] pass) {
		String parsedPass = new String(pass);
		System.out.println(user + "\n" + parsedPass);
		if(user.isEmpty() && parsedPass.isEmpty()){
			return "You must enter a username & password";
		}else if(user.isEmpty()){
			return "You must enter a username";
		}else if(parsedPass.isEmpty()){
			return "You must enter a password";
		}else if((user.isEmpty() && parsedPass.isEmpty()) == false){
			return user;
		}
		return "Failed Registration";
	}
}
