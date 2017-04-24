package cop4331.gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinancePanel extends JPanel {
	private static JPanel financePanel;
	int Quantity;
	float Invoiceprice;
	static JPanel financialinfo;
	float Sellingprice;

	/**
	 * Constructor for the finance panel
	 * 
	 * @param finances
	 *            Finance csv data from model
	 */
	public FinancePanel(List<String[]> finances) {
		financePanel = new JPanel(new WrapLayout());
		financePanel.setLayout(new WrapLayout());
		financePanel.setPreferredSize(new Dimension(650,500));
		JLabel financelabel = new JLabel("Finances...");
		financePanel.add(financelabel);

		for (String[] sale : finances) {
			if (sale[0].equals("Total")) {
				JPanel FinanceSalelabels = new JPanel(new GridLayout(0, 8));
				FinanceSalelabels.setBackground(new Color(200, 200, 200));
				FinanceSalelabels.add(new JLabel("total"));
				FinanceSalelabels.add(new JLabel("cost"));
				FinanceSalelabels.add(new JLabel("username"));
				FinanceSalelabels.add(new JLabel("first"));
				FinanceSalelabels.add(new JLabel("last"));
				FinanceSalelabels.add(new JLabel("cc"));
				FinanceSalelabels.add(new JLabel("email"));
				FinanceSalelabels.add(new JLabel("address"));
				FinanceSalelabels.setPreferredSize(new Dimension(600, 25));
				financePanel.add(FinanceSalelabels);
				continue;
			} else {
				JPanel FinanceSale = new JPanel(new GridLayout(0, 8));
				FinanceSale.setBackground(new Color(200, 200, 200));
				FinanceSale.add(new JLabel(sale[0]));
				FinanceSale.add(new JLabel(sale[1]));
				FinanceSale.add(new JLabel(sale[2]));
				FinanceSale.add(new JLabel(sale[3]));
				FinanceSale.add(new JLabel(sale[4]));
				FinanceSale.add(new JLabel(sale[5]));
				FinanceSale.add(new JLabel(sale[6]));
				FinanceSale.add(new JLabel(sale[7]));
				FinanceSale.setPreferredSize(new Dimension(600, 25));
				financePanel.add(FinanceSale);
			}
		}
		financialinfo = new JPanel(new WrapLayout());
		financePanel.add(financialinfo);
		financialinfo.setPreferredSize(new Dimension(400, 25));
	}

	/**
	 * Sets the finance panel profit
	 * 
	 * @param profit
	 *            Total sales
	 */
	public static void setProfit(float profit) {
		JLabel Profits = new JLabel("Profits: " + profit);
		financialinfo.add(Profits);
	}

	/**
	 * Sets the finance panel revenue
	 * 
	 * @param revenue
	 *            Profit - Cost
	 */
	public static void setRevenue(float revenue) {
		JLabel Revenue = new JLabel("Revenue: " + revenue + " | ");
		financialinfo.add(Revenue);
	}

	/**
	 * Sets the finance panel cost
	 * 
	 * @param cost
	 *            Total invoice price on sales
	 */
	public static void setCost(float cost) {
		JLabel Cost = new JLabel("Cost: " + cost + " | ");
		financialinfo.add(Cost);
	}

	/**
	 * Returns the finance panel for use
	 * 
	 * @return Finance JPanel
	 */
	public Component getPanel() {
		return financePanel;
	}

}
