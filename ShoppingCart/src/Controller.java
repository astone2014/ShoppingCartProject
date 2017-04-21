import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {
	private Model model;
	private View view;
	private LoginPanel login;
	private StorePanel store;
	private List<ActionListener> buyNowActionListenerList;

	/**
	 * Controls user input and is the master of model and view
	 * 
	 * @param model
	 *            the database
	 * @param view
	 *            the display
	 */
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		login = new LoginPanel();
		view.addPanel(login.getPanel());
		store = new StorePanel();

		login.addLoginListener(new LoginListener());
		login.addRegisterListener(new RegisterListener());
		login.addSignUpListener(new SignUpListener());
		view.viewRefresh();
	}

	// <-----------Login Listeners----------->
	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			if (login.getLoginStatus()) {
				store.getNav().addStoreListener(new StoreListener());
				store.getNav().addCheckoutListener(new CheckoutListener());
				store.getNav().addLogoutListener(new LogoutListener());
				if (model.loginUser(login.getUserName(), login.getUserPassword())) {
					view.removePanel(login.getPanel());
					view.addPanel(store.getPanel());
					store.viewProducts(model.getCSV("products.csv"), createBuyNowActionListeners(model.getCSV("products.csv").size()));
					store.getNav().addWelcomeMessage(("Welcome " + model.getAccountUsername()).toString());
					view.viewRefresh();
				} else {
					login.loginMessage("No account found. Please Try again.");
				}
			}else{
				login.viewLogin();
				view.viewRefresh();
			}
		}
	}

	// <--------Login Page Listeners------->
	class RegisterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			displayRegistration();
		}
	}

	class SignUpListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			if (model.signUpUser(login.getUserName(), login.getUserPassword())) {
				login.loginMessage("Signup Successful");
			} else {
				login.loginMessage("Please try different credentials");
			}
		}
	}

	// <---------Navigation Listeners-------->
	class StoreListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			displayStore();
		}
	}

	class CheckoutListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			displayCheckout();
		}
	}

	class LogoutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			displayLogin();
		}
	}

	class BuyNowListener implements ActionListener{
		private String Name;
		private float Price;
		
		public BuyNowListener(String name, String price) {
			Name = name;
			Price = Float.parseFloat(price);
		}

		public void actionPerformed(ActionEvent e) {
			model.cartAdd(Name, Price);
			System.out.println(Name);
		}
	}
	// <----------Helper Functions---------->
	/**
	 * Displays the store if it's not already displayed.
	 */
	public void displayStore() {
		System.out.println(store.getCurrentView());
		if (store.getCurrentView().equals("Checkout")) {
			System.out.println("Store Display");
			store.removeProductsFromDisplay();
			store.viewProducts(model.getCSV("products.csv"), createBuyNowActionListeners(model.getCSV("products.csv").size()));
			view.viewRefresh();
		} else {
			System.out.println("You're already on the store page.");
		}
	}

	private BuyNowListener[] createBuyNowActionListeners(int amount) {
		BuyNowListener[] buynowlistenerarray = new BuyNowListener[amount + 1];
		if (model.getCSV("products.csv").isEmpty())
			return null;
		else {
			int count = 0;
			for (String[] product : model.getCSV("products.csv")) {
				++count;
				buynowlistenerarray[count] = new BuyNowListener(product[0], product[1]);
			}
			return buynowlistenerarray;
		}
	}

	public void displayCheckout() {
		store.viewCheckout(model.getAccountCart());
		view.viewRefresh();
	}

	/**
	 * Displays the login if it's not already displayed.
	 */
	public void displayLogin() {
		System.out.println("Goodbye" + model.getAccountUsername());
		if (login.isLoginPanelDisplayed() == false) {
			view.removePanel(store.getPanel());
			view.addPanel(login.getPanel());
			view.viewRefresh();
		}
	}

	public void displayRegistration() {
		System.out.println("Signup here");
		if (login.getLoginStatus()) {
			login.viewRegistration();
			view.viewRefresh();
		}
	}
}
