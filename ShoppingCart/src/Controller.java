import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {
	private Model model;
	private View view;
	/**
	 * Controls user input and is the master of model and view
	 * @param model the database
	 * @param view the display
	 */
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
		
		this.view.addLoginListener(new LoginListener());
		this.view.addRegisterListener(new RegisterListener());
		this.view.addSignUpListener(new SignUpListener());
		this.view.addStoreListener(new StoreListener());
		this.view.addCheckoutListener(new CheckoutListener());
		this.view.addLogoutListener(new LogoutListener());
		List<String[]> products = model.getProducts();
		for(String[] row : products){
			this.view.addBuyNowListener(new BuyNowListener());
		}
	}
	
	class LoginListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			if(model.loginUser(view.getUserName(), view.getUserPassword())){
				view.viewLogedIn(model.getAccountType());
				view.viewProducts(model.getProducts());
			}else{
				view.loginMessage("No account found. Please Try again.");
			}
		}
	}
	
	class RegisterListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			view.viewLoginPage();
		}
	}
	
	class SignUpListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			if(model.signUpUser(view.getUserName(), view.getUserPassword())){
				view.loginMessage("Signup Successful");
			}else{
				view.loginMessage("Please try different credentials");
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
			view.viewLoginPage();
		}
	}
	
	class BuyNowListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			System.out.println("Gekko");
		}
	}
}
