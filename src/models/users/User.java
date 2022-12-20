package models.users;

public class User {
	private String role;

	protected User(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

}
