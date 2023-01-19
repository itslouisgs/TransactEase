package factories.users;

import models.users.Customer;
import models.users.User;

public class CustomerFactory implements UserFactory {

	@Override
	public User getUser(int id, String name, String email, String password) {
		// TODO Auto-generated method stub
		return new Customer(id, name, email, password);
	}

}
