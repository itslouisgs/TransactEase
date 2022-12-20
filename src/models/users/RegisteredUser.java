package models.users;

public class RegisteredUser extends User {
	private String name;
	private String email;
	private String password;
	
	protected RegisteredUser(String role, String name, String email, String password) {
		super(role);
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
