package models.users;

public abstract class User {
	private String role;
	
	protected User() {}

	protected User(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	abstract public void displayInformation();

}
