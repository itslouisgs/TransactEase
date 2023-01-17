package factories.users;

import models.users.Admin;
import models.users.User;

public class AdminFactory implements UserFactory {

	@Override
	public User getUser(int id, String name, String email, String password) {
		// TODO Auto-generated method stub
		return new Admin(id, name, email, password);
	}

}
