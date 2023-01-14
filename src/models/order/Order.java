package models.order;

import models.payments.Payments;

public class Order {
	private String title, type;
	private int price;
	private Payments payments;
	
	public Order(String title, String type, int price, Payments payments) {
		super();
		this.title = title;
		this.type = type;
		this.price = price;
		this.payments = payments;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Payments getPayments() {
		return payments;
	}
	public void setPayments(Payments payments) {
		this.payments = payments;
	}
	
}

