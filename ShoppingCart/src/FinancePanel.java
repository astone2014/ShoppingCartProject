import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinancePanel extends JPanel {
	private JPanel financePanel;
	private String Name;
	private String Description;
	private String ID;
	private String Type;
	int Quantity;
	float Invoiceprice;
	float Sellingprice;

	public FinancePanel(String sale, String sale2, String sale3, String sale4, String sale5, String sale6, String sale7) {
		financePanel = new JPanel();
		financePanel.setLayout(null);
		financePanel.add(new JLabel("Hey"));
	}

	public Component getPanel() {
		return financePanel;
	}

}
