import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class LoginWindow extends JFrame {
	private static LoginWindow frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("Failed to create login window" + e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 450, 300);
		
		Register register = new Register(); // initialize register
		
		Login login = new Login();
		login.setVisible(true);
		getContentPane().add(login);
		
		JButton btnRegister = new JButton("Register");
		login.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() { // switch between login and registration
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(login.isVisible()){
					register.add(btnRegister);
					login.setVisible(false);
					register.setVisible(true);
					getContentPane().add(register);
					refresh();
				}else{
					login.add(btnRegister);
					login.setVisible(true);
					register.setVisible(false);
					getContentPane().add(login);
					refresh();
				}
			}
		});
		btnRegister.setBounds(10, 11, 89, 23);
		
	}
	
	public void refresh(){
		getContentPane().revalidate();
		getContentPane().repaint();
	}
}
