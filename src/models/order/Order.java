package models.order;

import java.util.ArrayList;

import template.payments.Payments;

public class Order {
	private ArrayList<OrderDetail> orders = new ArrayList<>();
	private Payments payments;
	private double total;
	
	public Order(ArrayList<OrderDetail> orders, Payments payments) {
		super();
		this.orders = orders;
		this.payments = payments;
		this.total = calculateTotal();
	}
	
	public double calculateTotal() {
		for (OrderDetail orderDetail : orders) {
			total = orderDetail.calculateSubTotal() * 0.1;
		}
		return total;
	}
	
	
}
