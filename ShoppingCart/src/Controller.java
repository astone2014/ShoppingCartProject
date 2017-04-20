import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private Model model;
	private View view;
	
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
		
		this.view.addLoginListener(new LoginListener());
		this.view.addRegisterListener(new RegisterListener());
		this.view.addSignUpListener(new SignUpListener());
		this.view.addStoreListener(new StoreListener());
		this.view.addCheckoutListener(new CheckoutListener());
		this.view.addLogoutListener(new LogoutListener());
		this.view.addCartAddListener(new CartAddListener());
	}
	class LoginListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			if(model.loginUser(view.getUserName(), view.getUserPassword())){
				view.viewLogedIn(model.getAccountType());
			}else{
				view.loginMessage("No account found. Please Try again.");
			}
		}
	}
	
	class RegisterListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			view.viewRegister();
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
			view.viewRegister();
		}
	}
	
	class CartAddListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			System.out.println("added Item");
		}
	}
}
