package cop4331.gui;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Shopping Cart Project Group 19
 *
 * @author Austen Stone, Reshma Thomas
 *
 */
public class View extends JFrame {
	/**
	 * Constructs view
	 */
	public View() {
		viewRefresh();
	}

	/**
	 * Adds a panel to the view
	 * @param panel JPanel to add to view
	 */
	public void addPanel(JPanel panel) {
		getContentPane().add(panel);
	}

	/**
	 * Removes a panel to the view
	 * @param panel JPanel to remove from view
	 */
	public void removePanel(JPanel panel) {
		getContentPane().remove(panel);
	}

	/**
	 * Refreshes the view by repainting and revalidating
	 */
	public void viewRefresh() {
		getContentPane().repaint();
		getContentPane().revalidate();
	}
}
