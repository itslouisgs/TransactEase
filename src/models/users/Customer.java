package models.users;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DatabaseConnection;

public class Customer extends User {
	private String phone;
	private int points;
	private static DatabaseConnection db = DatabaseConnection.getInstance();
	
	public Customer(int id, String name, String email, String password) {
		super(id, name, email, password, "customer");
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
	
	public boolean insert() {
		PreparedStatement st = db.prepareStatement("insert into users (name, email, password, phone, points, is_admin) values (?, ?, ?, ?, ?, ?)");
		
		try {
			st.setString(1, getName());
			st.setString(2, getEmail());
			st.setString(3, getPassword());
			st.setString(4, getPhone());
			st.setInt(5, 0);
			st.setBoolean(6, false);
			return st.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
