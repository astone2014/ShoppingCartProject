import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Shopping Cart Project Group 19
 * 
 * @author Austen Stone, Reshma Thomas, Arian Tirado
 *
 */
public class View extends JFrame {
	private JPanel login;
	
	private final JLabel lblLogin = new JLabel("");

	/**
	 * Constructor for view
	 */
	public View() {
		LoginPanel lp = new LoginPanel();
		StorePanel st = new StorePanel();
		CheckoutPanel ch = new CheckoutPanel();
		
		viewRefresh();
	}

	/**
	 * Repaints and revalidates the content pane to display changes.
	 */
	
	void addPanel(JPanel panel){
		getContentPane().add(panel);
	}
	
	public void removePanel(JPanel panel) {
		getContentPane().remove(panel);
	}
	
	void viewRefresh() {
		getContentPane().repaint();
		getContentPane().revalidate();
	}


}
