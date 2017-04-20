import java.awt.Color;
import java.awt.GridLayout;
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
	
	/**
	 * Create the panel.
	 * 
	 * @param product4
	 * @param product3
	 * @param product2
	 * @param product
	 */
	public ProductPanel(String name, String price, String count, String description) {
		Name = name;
		Price = Float.parseFloat(price);
		Count = Integer.parseInt(count);
		Description = description;
		
		product = new JPanel(new GridLayout(4, 2));
		product.setName(Name);
		product.setBackground(Color.LIGHT_GRAY);
		product.setBorder(new EmptyBorder(10, 5, 10, 5));
		product.add(new JLabel(Name));
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon("C:\\Users\\auste\\Downloads\\product.png"));
		product.add(image);
		product.add(new JLabel("$" + Price));
		product.add(new JLabel("Stock: " + Count));
		JLabel proddesc = new JLabel(Description);
		proddesc.setBounds(100, 100, 100, 100);
		product.add(proddesc);
		JButton buynow = new JButton("Buy Now");
		product.add(buynow);
		buynow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked " + product.getName());
			}
		});
	}
	
	public JPanel getPanel(){
		return product;
	}
}
