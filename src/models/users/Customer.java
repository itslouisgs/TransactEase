package models.users;

public class Customer extends RegisteredUser {
	private String phone;
	private int points;

	public Customer(String name, String email, String password, String phone) {
		super(name, "customer", email, password);
		this.phone = phone;
		this.points = 0;
	}

	public String getPhone() {
		return phone;
	}

	public int getPoints() {
		return points;
	}
	
	public void addPoints(int value) {
		this.points += value; 
	}
	
	public void subtractPoints(int value) {
		this.points -= value; 
	}
}
