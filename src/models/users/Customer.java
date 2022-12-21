package models.users;

import facades.DatabaseFacade;

public class Customer extends RegisteredUser {
	private String phone;
	private int points;

	public Customer() {
		super("customer");
		this.points = 0;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	public void saveToDatabase() {
		DatabaseFacade.insertCustomer(this);
	}
	
	@Override
	public void displayInformation() {
		super.displayInformation();
		System.out.println("Phone: " + getPhone());
		System.out.println("Points: " + getPoints());
	}
}
