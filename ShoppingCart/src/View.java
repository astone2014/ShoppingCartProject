import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Shopping Cart Project Group 19
 *
 * @author Austen Stone, Reshma Thomas, Arian Tirado
 *
 */
public class View extends JFrame {

	public View() {
		viewRefresh();
	}

	void addPanel(JPanel panel) {
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
