package facades;

import factories.users.CustomerFactory;
import factories.users.UserFactory;
import models.users.Customer;
import models.users.User;
import session.LoggedInUser;

public class AuthFacade {
	public static AuthFacade instance;
	private String errorMsg = "";
	
	public static AuthFacade getInstance() {
		if(instance == null) {
			instance = new AuthFacade();
		}
		
		return instance;
	}
	
	public boolean register(String name, String email, String password, String confirm, String phone) {
		if(name.isEmpty() || email.isEmpty() || password.isEmpty() || password.isEmpty() || confirm.isEmpty() || phone.isEmpty()) {
			errorMsg = "All fields must be filled!";
			return false;
		}else if(!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
			errorMsg = "Email must be in a correct format!";
			return false;
		} else if(phone.length() != 12 || !phone.matches("^[0-9]*$")) {
			errorMsg = "Phone must be numeric and the length must be 12";
			return false;
		}else if(!password.matches("^[a-zA-Z0-9]*$")) {
			errorMsg = "Password must be alphanumeric";
			return false;
		} else if(!password.equals(confirm)) {
			errorMsg = "Password must be the same as confirm password";
			return false;
		} 
		
		UserFactory uf = new CustomerFactory();
		
		Customer customer = (Customer) uf.getUser();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setPhone(phone);
		if(!customer.insert()) {
			return false;
		}
		
		return true;
	}

	public boolean login(String email, String password) {
		if(email.isEmpty() || password.isEmpty()) {
			errorMsg = "Email and password must be filled!";
			return false;
		}else if(!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
			errorMsg = "Email must be in a correct format!";
			return false;
		} else if(!password.matches("^[a-zA-Z0-9]*$")) {
			errorMsg = "Password must be alphanumeric";
			return false;
		}
		
		User u = new User().authenticate(email, password);
		if(u == null) {
			errorMsg = "Wrong credential!";
			return false;
		} else {
			LoggedInUser.getInstance().setLogged(u);
			System.out.println(u.getId());
		}
		
		return true;
	}

	public void logout() {
		LoggedInUser.getInstance().removeLogged();
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
