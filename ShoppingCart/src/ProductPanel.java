import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ProductPanel extends JPanel {
	private JPanel product;
	private String Name;
	private float Price;
	private int Count;
	private String Description;
	private JButton buynow;
	private JButton increment;
	private JButton decrement;

	/**
	 * Creates product panel for shopping cart
	 *
	 * @param name
	 *            Name of product
	 * @param price
	 *            Price of product
	 * @param count
	 *            How many of the product is in stock
	 * @param description
	 *            Description of product
	 * @wbp.parser.constructor
	 *
	 */
	public ProductPanel(String name, String price, String count, String description, ActionListener buynowbutton) {
		Name = name;
		Price = Float.parseFloat(price);
		Count = Integer.parseInt(count);
		Description = description;

		product = new JPanel(new BorderLayout());
		product.setName(Name);
		product.setBackground(new Color(235, 232, 217));
		product.setBorder(new EmptyBorder(10, 5, 10, 5));

		product.add(prodImage("C:\\Users\\auste\\Downloads\\product.png"), BorderLayout.WEST);
		product.add(new JLabel(Name), BorderLayout.NORTH);
		
		JPanel store = new JPanel(new BorderLayout());
		JPanel checkoutInfo = new JPanel(new FlowLayout());
		JPanel prodDesc = new JPanel(new FlowLayout());
		store.add(buyNowButton(buynowbutton), BorderLayout.SOUTH);
		checkoutInfo.add(new JLabel("Stock: " + Count));
		checkoutInfo.add(new JLabel("$" + Price));
		prodDesc.add(prodDescription(description));
		store.add(checkoutInfo, BorderLayout.NORTH);
		store.add(prodDesc, BorderLayout.CENTER);
		store.setBackground(new Color(235, 232, 217));
		product.add(store, BorderLayout.EAST);

	}

	/**
	 * Creates product panel checkout
	 *
	 * @param name
	 *            Name of product
	 * @param price
	 *            Price of product
	 * @param count
	 *            How many of the product is in stock
	 */
	public ProductPanel(String name, String price, String count, ActionListener incrementListener,
			ActionListener decrementListenerr) {
		Name = name;
		Price = Float.parseFloat(price);
		Count = Integer.parseInt(count);

		product = new JPanel(new BorderLayout());
		product.setName(Name);
		product.setBackground(new Color(235, 232, 217));
		product.setBorder(new EmptyBorder(10, 5, 10, 5));

		product.add(prodImage("C:\\Users\\auste\\Downloads\\product.png"), BorderLayout.WEST);
		product.add(new JLabel(Name), BorderLayout.NORTH);

		JPanel checkout = new JPanel(new BorderLayout());
		JPanel checkoutButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel checkoutInfo = new JPanel(new FlowLayout());
		checkoutInfo.add(new JLabel("$" + Price));
		checkoutInfo.add(new JLabel("Amount: " + Count));
		checkoutButtons.add(incrementCartButton(incrementListener));
		checkoutButtons.add(decrementCartButton(decrementListenerr));
		checkout.add(checkoutInfo, BorderLayout.NORTH);
		checkout.add(checkoutButtons, BorderLayout.SOUTH);
		product.setBackground(new Color(235, 232, 217));
		product.add(checkout, BorderLayout.EAST);

	}

	public JPanel getPanel() {
		return product;
	}

	public JLabel prodImage(String imageLocation) {
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon(imageLocation));
		return image;
	}

	public JLabel prodDescription(String description) {
		JLabel prodDescription = new JLabel(description);
		return prodDescription;
	}

	public void addBuyNowListener(ActionListener listenerforBuyNow) {
		buynow.addActionListener(listenerforBuyNow);
	}

	public void addIncrementListener(ActionListener listenerForIncrement) {
		increment.addActionListener(listenerForIncrement);
	}

	public JButton buyNowButton(ActionListener buynowbutton) {
		buynow = new JButton("Buy Now");
		buynow.addActionListener(buynowbutton);
		return buynow;
	}

	public JButton incrementCartButton(ActionListener incrementButton) {
		increment = new JButton("^");
		increment.addActionListener(incrementButton);
		return increment;
	}
	
	public JButton decrementCartButton(ActionListener decrementButton) {
		increment = new JButton("v");
		increment.addActionListener(decrementButton);
		return increment;
	}
}
