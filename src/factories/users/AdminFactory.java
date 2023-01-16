package factories.users;

import models.users.Admin;
import models.users.User;

public class AdminFactory implements UserFactory {

	@Override
	public User getUser(String name, String email, String password) {
		return new Admin(name, email, password);
	}

}
