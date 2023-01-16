package factories.users;

import models.users.Customer;
import models.users.User;

public class CustomerFactory implements RegisteredUserFactory {

	@Override
	public User getUser() {
		return new Customer();
	}
}
