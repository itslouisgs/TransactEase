package models.users;

public class RegisteredUser extends User {
	private String name;
	private String email;
	private String password;
	
	protected RegisteredUser(String role) {
		super(role);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void displayInformation() {
		System.out.println("Name: " + getName());
		System.out.println("Email: " + getEmail());
		System.out.println("Role: " + getRole());
	}
}
