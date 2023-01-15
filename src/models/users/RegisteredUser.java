package models.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
import factories.users.AdminFactory;
import factories.users.CustomerFactory;
import factories.users.RegisteredUserFactory;

public class RegisteredUser extends User {
	private String name;
	private String email;
	private String password;
	private DatabaseConnection db = DatabaseConnection.getInstance();
	
	public RegisteredUser() {}
	
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
	
	public RegisteredUser map(ResultSet rs) {
		RegisteredUserFactory factory = null;
		RegisteredUser user = null;
		
		try {
			if (rs.getBoolean("is_admin")) {
				factory = new AdminFactory();
			} else {
				factory = new CustomerFactory();
			}
			
			user = (RegisteredUser) factory.getUser();
			
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			
			if (user.getRole().equalsIgnoreCase("customer")) {
				((Customer) user).setPhone(rs.getString("phone"));
				((Customer) user).addPoints(rs.getInt("points"));				
			}
		} catch (SQLException e) {}

		return user;
	}
	
	public RegisteredUser authenticate(String email, String password) {
		PreparedStatement st = db.prepareStatement("select * from users where email = ? and password = ?");
		RegisteredUser user = null;
		
		try {
			st.setString(1, email);
			st.setString(2, password);
			ResultSet results = st.executeQuery();
			
			if (results.first()) {
				user = map(results);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;	
	}
}
