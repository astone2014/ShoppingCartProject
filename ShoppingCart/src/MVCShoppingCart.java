
public class MVCShoppingCart {

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(model, view);
		view.setBounds(100, 100, 500, 500);
		view.setVisible(true);
	}

}
