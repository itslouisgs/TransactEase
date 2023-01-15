package template.payments;

import java.util.Random;

import javax.swing.JPanel;

import models.order.Order;

public abstract class Payments {
	private String paymentName;
	private String paymentId;
	
	public Payments(String paymentName) {
		super();
		this.paymentName = paymentName;
		this.paymentId = "";
		for(int i = 0; i < 8; i++) {
			this.paymentId += getRandomID();
		}
	}

	private char getRandomID() {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		return base.charAt(new Random().nextInt(base.length()));
	}

	public abstract JPanel displayPaymentDetail();
	
}
