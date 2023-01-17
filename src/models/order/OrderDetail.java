package models.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.DatabaseConnection;
import models.products.Product;
import template.payments.CashPayments;
import template.payments.CreditCardPayments;
import template.payments.EMoneyPayments;

public class OrderDetail {
	private Product product;
	private int quantity;
	
	private DatabaseConnection con = DatabaseConnection.getInstance();
	
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
	
	private OrderDetail map(ResultSet rs) {
		String productId;
		int id, qty;
		
		try {
			id = rs.getInt("id");
			productId = rs.getString("product_id");
			qty = rs.getInt("qty");
			
			return new OrderDetail(new Product().getProduct(productId), qty);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Vector<OrderDetail> getAllByOrder(String orderId) {
		System.out.println(orderId);
		String query = String.format("SELECT * FROM order_details WHERE order_id = '%s'", orderId);
		
		ResultSet rs = con.executeQuery(query);
		Vector<OrderDetail> orders = new Vector<>();
		try {
			while(rs.next()) {
				OrderDetail order = map(rs);
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insert(String orderId) {
		System.out.println("halo");
		String query = String.format("INSERT INTO order_details (order_id, product_id, qty) VALUES (?, ?, ?)");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, orderId);
			ps.setString(2, getProduct().getId());
			ps.setInt(3, quantity);
			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
