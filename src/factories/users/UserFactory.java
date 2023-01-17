package factories.users;

import models.users.User;

public interface UserFactory {
	public User getUser(int id, String name, String email, String password);
}
