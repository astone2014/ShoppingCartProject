import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private Model model;
	private View view;
	
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
		
		this.view.addLoginListener(new LoginListener());
	}
	class LoginListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			model.loginUser(view.getUserName(), view.getUserPassword());
			System.out.println("Login action performed");
		}
	}
}
