package factories.users;

import models.users.Customer;
import models.users.RegisteredUser;

public class CustomerFactory implements RegisteredUserFactory {

	@Override
	public RegisteredUser getUser() {
		return new Customer();
	}
}
