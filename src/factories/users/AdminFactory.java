package factories.users;

import models.users.Admin;
import models.users.RegisteredUser;

public class AdminFactory implements RegisteredUserFactory {

	@Override
	public RegisteredUser getUser() {
		return new Admin();
	}

}
