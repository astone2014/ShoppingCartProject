import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private Model model;
	private View view;
	private LoginPanel login;
	private StorePanel store;

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
		public void actionPerformed(ActionEvent a) {
			if (login.getLoginStatus()) {
				store.getNav().addStoreListener(new StoreListener());
				store.getNav().addCheckoutListener(new CheckoutListener());
				store.getNav().addLogoutListener(new LogoutListener());
				if (model.loginUser(login.getUserName(), login.getUserPassword())) {
					model.setAccountUsername(login.getUserName());
					view.removePanel(login.getPanel());
					view.addPanel(store.getPanel());
					store.viewProducts(model.getCSV("products.csv"),
							createBuyNowListeners(model.getCSV("products.csv").size()));
					store.getNav().addWelcomeMessage(
							("Welcome " + model.getAccountTypeString() + " " + model.getAccountUsername()));
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
		public void actionPerformed(ActionEvent a) {
			displayLogin();
		}
	}

	class BuyNowListener implements ActionListener {
		private String ID;
		private String Name;
		private String Type;
		private String Price;

		public BuyNowListener(String id, String name, String type, String price) {
			ID = id;
			Name = name;
			Type = type;
			Price = price;
		}

		public void actionPerformed(ActionEvent e) {
			model.cartAdd(ID, Name, Type, Price);
			store.viewProducts(model.getCSV("products.csv"),
					createBuyNowListeners(model.getCSV("products.csv").size()));
			view.viewRefresh();
		}
	}

	class IncrementListener implements ActionListener {
		private String ID;
		private String Name;
		private String Type;
		private String Price;
		/**
		 * creates increment listener
		 * @param name
		 * @param price
		 * @param id 
		 * @param type 
		 */
		public IncrementListener(String id, String name, String type, String price) {
			ID = id;
			Name = name;
			Type = type;
			Price = price;
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("increment");
			model.cartAdd(ID, Name, Type, Price);
			store.viewCheckout(model.getCSV(model.getAccountCSVLocation()),
					createIncrementListeners(model.getCSV(model.getAccountCSVLocation()).size()),
					createDecrementListeners(model.getCSV(model.getAccountCSVLocation()).size()));
			view.viewRefresh();
		}
	}

	class DecrementListener implements ActionListener {
		private String ID;
		private String Name;
		private String Type;
		private String Price;

		public DecrementListener(String id, String name, String type, String price) {
			ID = id;
			Name = name;
			Type = type;
			Price = price;
		}

		public void actionPerformed(ActionEvent e) {
			model.cartRemove(ID, Name, Type, Price);
			store.viewCheckout(model.getCSV(model.getAccountCSVLocation()),
					createIncrementListeners(model.getCSV(model.getAccountCSVLocation()).size()),
					createDecrementListeners(model.getCSV(model.getAccountCSVLocation()).size()));
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
		BuyNowListener[] buynowlistenerarray = new BuyNowListener[amount];
		if (model.getCSV("products.csv").isEmpty())
			return null;
		else {
			int count = 0;
			for (String[] product : model.getCSV("products.csv")) {
				if(product[0].equals("ID")){
					System.out.println("FIRST LINE");
					continue;
				}
				++count;
				String name = product[5];
				String price = product[4];
				String type = product[1];
				String id = product[0];
				buynowlistenerarray[count] = new BuyNowListener(id, name, type, price);
			}
			return buynowlistenerarray;
		}
	}

	private IncrementListener[] createIncrementListeners(int amount) {
		IncrementListener[] incrementlistenerarray = new IncrementListener[amount];
		if (model.getCSV(model.getAccountCSVLocation()).isEmpty())
			return null;
		else {
			int count = 0;
			for (String[] product : model.getCSV(model.getAccountCSVLocation())) {
				if(product[0].equals("ID")){
					System.out.println("FIRST LINE");
					continue;
				}
				String id = product[0];
				String name = product[1];
				String type = product[2];
				String price = product[3];
				incrementlistenerarray[count] = new IncrementListener(id, name, type, price);
				++count;
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
				if(product[0].equals("ID")){
					System.out.println("FIRST LINE");
					continue;
				}
				String id = product[0];
				String name = product[1];
				String type = product[2];
				String price = product[3];
				decrementlistenerarray[count] = new DecrementListener(id, name, type, price);
				++count;
			}
			return decrementlistenerarray;
		}
	}

	/**
	 * Displays the checkout
	 */
	public void displayCheckout() {
		if (store.getCurrentView().equals("Store")) {
			store.removeProductsFromDisplay();
			store.viewCheckout(model.getAccountCart(),
					createIncrementListeners(model.getCSV(model.getAccountCSVLocation()).size()),
					createDecrementListeners(model.getCSV(model.getAccountCSVLocation()).size()));
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
