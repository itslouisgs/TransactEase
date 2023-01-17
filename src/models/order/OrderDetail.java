package models.order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.DatabaseConnection;
import models.products.Product;

public class OrderDetail {
	private Product product;
	private int quantity;
	private DatabaseConnection con = DatabaseConnection.getInstance();
	private String id;
		
	public OrderDetail() {}

	public OrderDetail(Product product) {
		this.product = product;
		this.quantity = 1;
	}
	
	public OrderDetail(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void addQuantity() {
		this.quantity += 1;
	}
	
	public double calculateSubTotal() {
		double subTotal;
		
		subTotal = product.getPrice() * quantity;
		return subTotal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean insertOrder() {
		String query = String.format("INSERT INTO OrderDetails (id,name, price,quantity) VALUES (?, ?, ?,?)");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, id);
			ps.setString(2, product.getName());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, quantity);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete() {
		String query = String.format("DELETE FROM OrderDetails WHERE id=?");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, id);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	

}
