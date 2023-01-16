package factories.users;

import models.users.Customer;
import models.users.User;

public class CustomerFactory implements UserFactory {

	@Override
	public User getUser() {
		return new Customer();
	}
}
