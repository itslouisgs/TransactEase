package facades;

import factories.users.CustomerFactory;
import factories.users.RegisteredUserFactory;
import models.users.Customer;
import models.users.Guest;
import models.users.User;

public class AuthFacade {
	private static RegisteredUserFactory registeredUserFactory;
	
	public static User register(String name, String email, String password, String phone) {
		registeredUserFactory = new CustomerFactory();
		
		Customer customer = (Customer) registeredUserFactory.getUser();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setPhone(phone);
		customer.saveToDatabase();
		
		return customer;
	}
	
	public static User loginAsGuest() {
		return new Guest();
	}
	
	public static User login(String username, String password) {
		return DatabaseFacade.authenticate(username, password);
	}
	
	public static User logout() {
		return null;
	}

}
