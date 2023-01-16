package models.order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import database.DatabaseConnection;
import template.payments.Payments;

public class Order {
	private Vector<OrderDetail> orders = new Vector<>();
	private Payments payments;
	private String id;
	private DatabaseConnection con = DatabaseConnection.getInstance();
	
	public Order() {}
	
	public Order(Vector<OrderDetail> orders, Payments payments, String id) {
		this.orders = orders;
		this.payments = payments;
		this.id = id;
	}
	
	public Double calculateSubotal() {
		Double total = (double) 0;
		for (OrderDetail orderDetail : orders) {
			total += orderDetail.calculateSubTotal();
		}
		return total;
	}
	
	public Double calculateTax() {
		Double total = (double) 0;
		for (OrderDetail orderDetail : orders) {
			total += orderDetail.calculateSubTotal() * 0.1;
		}
		return total;
	}
	
	public Double calculateTotal() {
		Double total = (double) 0;
		for (OrderDetail orderDetail : orders) {
			total += orderDetail.calculateSubTotal() * 1.1;
		}
		return total;
	}
	
	public void addOrder(OrderDetail od) {
		orders.add(od);
	}

	public Vector<OrderDetail> getOrders() {
		return orders;
	}

	public void setOrders(Vector<OrderDetail> orders) {
		this.orders = orders;
	}

	public Payments getPayments() {
		return payments;
	}

	public void setPayments(Payments payments) {
		this.payments = payments;
	}
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean insertOrder() {
		String query = String.format("INSERT INTO Orders (id,payment_id, payment_type) VALUES (?, ?, ?)");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, id);
			ps.setString(2, payments.getPaymentId());
			ps.setString(3, payments.getPaymentName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete() {
		String query = String.format("DELETE FROM Orders WHERE id=?");
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
