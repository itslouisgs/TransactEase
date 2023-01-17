package factories.users;

import models.users.User;

public interface UserFactory {
	public User getUser(String name, String email, String password);
}
