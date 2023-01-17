package models.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
import factories.users.AdminFactory;
import factories.users.CustomerFactory;
import factories.users.UserFactory;

public class User {
<<<<<<< HEAD
	private int id;
=======
>>>>>>> 6225c82a4c736dc76736a99ed47b6209acfa2a0f
	private String name;
	private String email;
	private String password;
	private String role;
	private DatabaseConnection db = DatabaseConnection.getInstance();
	
	public User() {}
	
<<<<<<< HEAD
	protected User(String role) {
		super();
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

=======
	protected User(String name, String email, String password, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

>>>>>>> 6225c82a4c736dc76736a99ed47b6209acfa2a0f
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

	public String getRole() {
		return role;
	}
<<<<<<< HEAD
	
	public User map(ResultSet rs) {
		UserFactory factory = null;
		User user = null;
		
		try {
			if (rs.getBoolean("is_admin")) {
				factory = new AdminFactory();
			} else {
				factory = new CustomerFactory();
			}
			
			user = (User) factory.getUser();
			
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			
			if (user.getRole().equalsIgnoreCase("customer")) {
				((Customer) user).setPhone(rs.getString("phone"));
				((Customer) user).addPoints(rs.getInt("points"));				
			}
		} catch (SQLException e) {}

=======

	public void displayInformation() {
		System.out.println("Name: " + getName());
		System.out.println("Email: " + getEmail());
		System.out.println("Role: " + getRole());
	}
	
	public User map(ResultSet rs) {
		UserFactory factory = null;
		User user = null;
		
		try {
			if (rs.getBoolean("is_admin")) {
				factory = new AdminFactory();
			} else {
				factory = new CustomerFactory();
			}
			
			user = factory.getUser(
					rs.getString("name"), 
					rs.getString("email"), 
					rs.getString("password")
				);
			
			if (user.getRole().equalsIgnoreCase("customer")) {
				((Customer) user).setPhone(rs.getString("phone"));
				((Customer) user).addPoints(rs.getInt("points"));				
			}
		} catch (SQLException e) {}

>>>>>>> 6225c82a4c736dc76736a99ed47b6209acfa2a0f
		return user;
	}
	
	public User authenticate(String email, String password) {
		PreparedStatement st = db.prepareStatement("select * from users where email = ? and password = ?");
		User user = null;
		
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
