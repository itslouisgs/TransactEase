package models.users;

public class Guest extends User {
	private int id;
	private static int incrementer = 1;

	public Guest() {
		super("guest");
		this.id = incrementer++;
	}

	public int getId() {
		return id;
	}

	@Override
	public void displayInformation() {
		System.out.println("You're logged in as Guest #" + getId());
	}
}
