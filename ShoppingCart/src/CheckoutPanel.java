import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CheckoutPanel extends JPanel {
	private JPanel checkout;
	private JPanel checkoutPanel;
	private JButton btnCheckout;
	
	/**
	 * Create the panel.
	 */
	public CheckoutPanel() {
		checkout = new JPanel();
		checkoutPanel = new JPanel();
		checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		checkoutPanel.setBounds(100, 76, 75, 75);
		checkoutPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
	}
	/**
	 * displays checkout page and reload data.
	 *
	 * @param products
	 */
	void viewCheckout(List<String[]> products) {
		if (checkoutPanel.getParent() == null) {
			for (String[] product : products) {
				JPanel newCartItem = createCartItemJpanel(product[0], product[1], product[2]);
				checkoutPanel.add(newCartItem);
			}
		}
	}
	
	/**
	 * Creates a JPanel for the cart item
	 *
	 * @param Name
	 *            name of item
	 * @param Price
	 *            price of item
	 * @param Count
	 *            count of item
	 * @return
	 */
	JPanel createCartItemJpanel(String Name, String Price, String Count) {
		JPanel newproduct = new JPanel(new GridLayout(4, 2));
		newproduct.setBackground(Color.LIGHT_GRAY);
		newproduct.add(new JLabel(Name));
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon("C:\\Users\\auste\\Downloads\\product.png"));
		newproduct.add(image);
		newproduct.add(new JLabel("$" + Price));
		newproduct.add(new JLabel("Stock: " + Count));
		return newproduct;
	}

	public void addCheckoutListener(ActionListener listenerForCheckout) {
		btnCheckout.addActionListener(listenerForCheckout);
	}
}
