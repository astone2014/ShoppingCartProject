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

	// <----------Login Listeners---------->
	class LoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			if (login.getLoginStatus()) {
				store.getNav().addStoreListener(new StoreListener());
				store.getNav().addCheckoutListener(new CheckoutListener());
				store.getNav().addLogoutListener(new LogoutListener());
				if (model.loginUser(login.getUserName(), login.getUserPassword())) {
					model.setAccountUsername(login.getUserName());
					store.getNav().addWelcomeMessage(("Welcome " + model.getAccountUsername()).toString());
					view.removePanel(login.getPanel());
					view.addPanel(store.getPanel());
					store.viewProducts(model.getCSV("products.csv"),
							createBuyNowListeners(model.getCSV("products.csv").size()));
					view.viewRefresh();
				} else {
					login.loginMessage("No account found. Please Try again.");
				}
			} else {
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

	// <--------Navigation Listeners------->
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

	class BuyNowListener implements ActionListener {
		private String Name;
		private float Price;

		public BuyNowListener(String name, String price) {
			Name = name;
			Price = Float.parseFloat(price);
		}

		public void actionPerformed(ActionEvent e) {
			model.cartAdd(Name, Price);
			store.viewProducts(model.getCSV("products.csv"), createBuyNowListeners(model.getCSV("products.csv").size()));
			view.viewRefresh();
		}
	}
	
	class IncrementListener implements ActionListener {
		private String Name;
		private float Price;

		public IncrementListener(String name, String price) {
			Name = name;
			Price = Float.parseFloat(price);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("increment");
			model.cartAdd(Name, Price);
			store.viewCheckout(model.getCSV(model.getAccountCSVLocation()), createIncrementListeners(model.getCSV(model.getAccountCSVLocation()).size()), createDecrementListeners(model.getCSV(model.getAccountCSVLocation()).size()));
			view.viewRefresh();
		}
	}
	
	class DecrementListener implements ActionListener {
		private String Name;
		private float Price;

		public DecrementListener(String name, String price) {
			Name = name;
			Price = Float.parseFloat(price);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("increment");
			model.cartRemove(Name, Price);
			store.viewCheckout(model.getCSV(model.getAccountCSVLocation()), createIncrementListeners(model.getCSV(model.getAccountCSVLocation()).size()), createDecrementListeners(model.getCSV(model.getAccountCSVLocation()).size()));
			view.viewRefresh();
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
			store.viewProducts(model.getCSV("products.csv"),
					createBuyNowListeners(model.getCSV("products.csv").size()));
			view.viewRefresh();
		} else {
			System.out.println("You're already on the store page.");
		}
	}

	/**
	 * Creates button listeners.
	 * 
	 * @param Amount
	 *            how many listeners
	 * @return An array of BuyNowListeners
	 */
	private BuyNowListener[] createBuyNowListeners(int amount) {
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
	
	private IncrementListener[] createIncrementListeners(int amount) {
		IncrementListener[] incrementlistenerarray = new IncrementListener[amount + 1];
		if (model.getCSV(model.getAccountCSVLocation()).isEmpty())
			return null;
		else {
			int count = 0;
			for (String[] product : model.getCSV(model.getAccountCSVLocation())) {
				++count;
				incrementlistenerarray[count] = new IncrementListener(product[0], product[1]);
			}
			return incrementlistenerarray;
		}
	}
	
	private DecrementListener[] createDecrementListeners(int amount) {
		DecrementListener[] decrementlistenerarray = new DecrementListener[amount + 1];
		if (model.getCSV(model.getAccountCSVLocation()).isEmpty())
			return null;
		else {
			int count = 0;
			for (String[] product : model.getCSV(model.getAccountCSVLocation())) {
				++count;
				decrementlistenerarray[count] = new DecrementListener(product[0], product[1]);
			}
			return decrementlistenerarray;
		}
	}

	/**
	 * Displays the checkout
	 */
	public void displayCheckout() {
		System.out.println(store.getCurrentView());
		if (store.getCurrentView().equals("Store")) {
			System.out.println("Checkout Display");
			store.removeProductsFromDisplay();
			store.viewCheckout(model.getAccountCart(), createIncrementListeners(model.getCSV(model.getAccountCSVLocation()).size()), createDecrementListeners(model.getCSV(model.getAccountCSVLocation()).size()));
			view.viewRefresh();
		} else {
			System.out.println("You're already on the checkout page.");
		}
	}

	/**
	 * Displays the login if it's not already displayed.
	 */
	public void displayLogin() {
		System.out.println("Goodbye " + model.getAccountUsername());
		if (login.isLoginPanelDisplayed() == false) {
			view.removePanel(store.getPanel());
			view.addPanel(login.getPanel());
			view.viewRefresh();
		}
	}

	/**
	 * Displays the signup page
	 */
	public void displayRegistration() {
		System.out.println("Signup here");
		if (login.getLoginStatus()) {
			login.viewRegistration();
			view.viewRefresh();
		}
	}
}
