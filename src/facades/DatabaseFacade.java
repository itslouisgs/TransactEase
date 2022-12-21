package facades;

import database.DatabaseConnection;
import models.users.Customer;
import models.users.User;

public class DatabaseFacade {
//	private static DatabaseConnection db = DatabaseConnection.getInstance();
	
	public static void insertCustomer(Customer customer) {
		System.out.println("this: " + customer.getName());
		
//		PreparedStatement st = db.prepareStatement("insert into users values (?, ?, ?, ?)");
//		db.prepareStatement("");
	}
	
	public static User authenticate(String username, String password) {
		System.out.println("Authentication successfull");
		
		return new Customer();
	}

}
