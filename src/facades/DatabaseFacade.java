package facades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
import factories.users.AdminFactory;
import factories.users.CustomerFactory;
import factories.users.RegisteredUserFactory;
import models.users.Customer;
import models.users.RegisteredUser;

public class DatabaseFacade {
	private static DatabaseConnection db = DatabaseConnection.getInstance();
	
	public static void insertCustomer(Customer customer) {
		PreparedStatement st = db.prepareStatement("insert into users (name, email, password, phone, points, is_admin) values (?, ?, ?, ?, ?, ?)");
		
		try {
			st.setString(1, customer.getName());
			st.setString(2, customer.getEmail());
			st.setString(3, customer.getPassword());
			st.setString(4, customer.getPhone());
			st.setInt(5, 0);
			st.setBoolean(6, false);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static RegisteredUser authenticate(String email, String password) {
		PreparedStatement st = db.prepareStatement("select * from users where email = ? and password = ?");
		RegisteredUser user = null;
		
		RegisteredUserFactory factory = null;
		
		try {
			st.setString(1, email);
			st.setString(2, password);
			ResultSet results = st.executeQuery();
			
			while (results.next()) {
				if (results.getBoolean("is_admin")) {
					factory = new AdminFactory();
				} else {
					factory = new CustomerFactory();
				}
				
				user = (RegisteredUser) factory.getUser();
				user.setName(results.getString("name"));
				user.setEmail(results.getString("email"));
				user.setPassword(results.getString("password"));
				
				if (user.getRole().equalsIgnoreCase("customer")) {
					((Customer) user).setPhone(results.getString("phone"));
					((Customer) user).addPoints(results.getInt("points"));				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;	
	}

}
