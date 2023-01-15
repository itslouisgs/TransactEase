package models.order;

import java.util.Vector;

import template.payments.Payments;

public class Order {
	private Vector<OrderDetail> orders = new Vector<>();
	private Payments payments;
	
	public Order() {}
	
	public Order(Vector<OrderDetail> orders, Payments payments) {
		this.orders = orders;
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
}
