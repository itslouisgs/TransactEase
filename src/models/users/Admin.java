package models.users;

public class Admin extends RegisteredUser {

	public Admin(String name, String email, String password) {
		super(name, "admin", email, password);
	}
	
}
