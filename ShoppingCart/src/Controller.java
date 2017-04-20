import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {
	private Model model;
	private View view;
	private LoginPanel login;
	private StorePanel store;
	/**
	 * Controls user input and is the master of model and view
	 * @param model the database
	 * @param view the display
	 */
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
		login = new LoginPanel();
		view.addPanel(login.getPanel());
		store = new StorePanel();
		
		login.addLoginListener(new LoginListener());
		login.addLogoutListener(new LogoutListener());
		login.addRegisterListener(new RegisterListener());
		login.addSignUpListener(new SignUpListener());
		//store.addStoreListener(new StoreListener());
		//checkout.addCheckoutListener(new CheckoutListener());
		view.viewRefresh();
	}
	
	class LoginListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			System.out.println("Clicked Login");
			if(model.loginUser(login.getUserName(), login.getUserPassword())){
				view.removePanel(login.getPanel());
				view.addPanel(store.getPanel());
				store.viewProducts(model.getProducts());
				view.viewRefresh();
			}else{
				login.loginMessage("No account found. Please Try again.");
			}
		}
	}
	
	class RegisterListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			System.out.println("Clicked Register");
			login.viewLoginPage();
		}
	}
	
	class SignUpListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			if(model.signUpUser(login.getUserName(), login.getUserPassword())){
				login.loginMessage("Signup Successful");
			}else{
				login.loginMessage("Please try different credentials");
			}
		}
	}
	
	class StoreListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			view.viewProducts(model.getProducts());
		}
	}
	
	class CheckoutListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			view.viewCheckout(model.getAccountCart());
		}
	}
	
	class LogoutListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			login.viewLoginPage();
		}
	}
	
	class BuyNowListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			System.out.println("Gekko");
		}
	}
}
