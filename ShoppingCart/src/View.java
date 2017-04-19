import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class View extends JFrame {
	private JTextField userName = new JTextField(10);
	private JPasswordField userPassword = new JPasswordField(10);
	private JLabel lblUsername = new JLabel("Username:");
	private JLabel lblPassword = new JLabel("Password:");
	private final JButton btnLogin = new JButton("Login");

	public View(){
		JPanel login = new JPanel();
		login.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		login.add(userName);
		userName.setBounds(171, 100, 90, 20);
		userPassword.setBounds(171, 142, 90, 20);
		login.add(userPassword);
		lblUsername.setBounds(72, 115, 68, 14);
		login.add(lblUsername);
		lblPassword.setBounds(72, 145, 63, 14);
		login.add(lblPassword);
		btnLogin.setBounds(171, 173, 89, 23);
		login.add(btnLogin);
		
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
}
