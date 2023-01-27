package models.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.DatabaseConnection;
import session.LoggedInUser;
import template.payments.CashPayments;
import template.payments.CreditCardPayments;
import template.payments.EMoneyPayments;
import template.payments.Payments;

public class Order {
	private String id;
	private int userId;
	private Payments payments;
	private Vector<OrderDetail> orders = new Vector<>();

	private DatabaseConnection con = DatabaseConnection.getInstance();
	
	public Order() {
		this.id = generateId();
		this.userId = LoggedInUser.getInstance().getLogged().getId();
	}
	
	public Order(String id, int userId, Payments payments) {
		this.id = id;
		this.payments = payments;
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
	
	private Order map(ResultSet rs) {
		String id, paymentId, type;
		int userId;
		
		try {
			id = rs.getString("id");
			paymentId = rs.getString("payment_id");
			type = rs.getString("payment_type");
			userId = rs.getInt("user_id");
						
			if(type.equals("Credit Card")) {
				return new Order(id, userId, new CreditCardPayments(paymentId));
			}else if(type.equals("E-Money")) {
				return new Order(id, userId, new EMoneyPayments(paymentId));
			}
			
			return new Order(id, userId, new CashPayments(paymentId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<Order> getAll(){
		String query = String.format("SELECT * FROM Orders");
		ResultSet rs = con.executeQuery(query);
		Vector<Order> orders = new Vector<>();
		try {
			while(rs.next()) {
				Order order = map(rs);
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Order order : orders) {
			Vector<OrderDetail> ods = new OrderDetail().getAllByOrder(order.getId());

			order.setOrders(ods);
			return orders;
		}
		
		return null;
	}
	
	public Vector<Order> getAllByUser(){
		if(LoggedInUser.getInstance().getLogged() != null) {
			String query = String.format("SELECT * FROM Orders WHERE user_id = %d", LoggedInUser.getInstance().getLogged().getId());
			ResultSet rs = con.executeQuery(query);
			Vector<Order> orders = new Vector<>();
			try {
				while(rs.next()) {
					Order order = map(rs);
					orders.add(order);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (Order order : orders) {
				Vector<OrderDetail> ods = new OrderDetail().getAllByOrder(order.getId());

				order.setOrders(ods);
			}
			return orders;
		}
		
		return null;
	}
	
	public String getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public boolean insertOrder() {
		String query = String.format("INSERT INTO Orders (id, payment_id, payment_type, user_id) VALUES (?, ?, ?, ?)");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, id);
			ps.setString(2, payments.getPaymentId());
			ps.setString(3, payments.getPaymentName());
			ps.setInt(4, userId);
			
			if(ps.executeUpdate() == 0) return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (OrderDetail od : orders) {
			if (!od.insert(id)) return false;
		}
		
		return true;
	}
	
	private String generateId() {
		return String.format("O%03d", getAll() != null ?  getAll().size() + 1 : 1);
	}
	
}
