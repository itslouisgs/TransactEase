package template.payments;

import java.util.Random;

import models.order.Order;

public abstract class Payments {
	private String paymentName;
	private String paymentId;
	private Order orders;
	
	public Payments(String paymentName,Order orders) {
		super();
		this.paymentName = paymentName;
		this.paymentId = "";
		for(int i = 0; i < 8; i++) {
			this.paymentId += getRandomID();
		}
		this.orders = orders;
	}

	private char getRandomID() {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		return base.charAt(new Random().nextInt(base.length()));
	}
	
	public final void payments() {
		displayPaymentDetail(orders);
		paymentType();
	}
	
	protected abstract void displayPaymentDetail(Order orders);
	
	protected void paymentType() {
		System.out.println("================");
		System.out.println("PAY ID: "+ paymentId);
		System.out.println("Payment using: "+ paymentName);
		System.out.println("================");
	}
	
	
	
	
	
	
	
	
	
}
