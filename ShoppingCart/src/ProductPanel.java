import java.awt.Color;
import java.awt.event.ActionEvent;
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
	 */
	public ProductPanel(String name, String price, String count, String description, ActionListener buynowbutton) {
		Name = name;
		Price = Float.parseFloat(price);
		Count = Integer.parseInt(count);
		Description = description;

		product = new JPanel();
		product.setName(Name);
		product.setBackground(new Color(235, 232, 217));
		product.setBorder(new EmptyBorder(10, 5, 10, 5));

		product.add(prodImage("C:\\Users\\auste\\Downloads\\product.png"));
		product.add(new JLabel(Name));
		product.add(new JLabel("$" + Price));
		product.add(new JLabel("Stock: " + Count));

		product.add(prodDescription(description));
		product.add(buyNowButton(buynowbutton));

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
	public ProductPanel(String name, String price, String count) {
		Name = name;
		Price = Float.parseFloat(price);
		Count = Integer.parseInt(count);

		product = new JPanel();
		product.setName(Name);
		product.setBackground(new Color(235, 232, 217));
		product.setBorder(new EmptyBorder(10, 5, 10, 5));

		product.add(prodImage("C:\\Users\\auste\\Downloads\\product.png"));
		product.add(new JLabel(Name));
		product.add(new JLabel("$" + Price));
		product.add(new JLabel("Stock: " + Count));

		product.add(incrementCartButton());

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
	
	public void addBuyNowListener(ActionListener listenerForLogout) {
		buynow.addActionListener(listenerForLogout);
	}
	
	public JButton buyNowButton(ActionListener buynowbutton) {
		buynow = new JButton("Buy Now");
		buynow.addActionListener(buynowbutton);
		return buynow;
	}

	public JButton incrementCartButton() {
		JButton buynow = new JButton("^");
		buynow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("BuyNow " + product.getName());
			}
		});
		return buynow;
	}
}
