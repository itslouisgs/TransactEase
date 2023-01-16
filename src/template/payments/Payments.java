package template.payments;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	public final JPanel payments() {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		JPanel display = displayPaymentDetail();
		JPanel label = paymentType();
		
		panel.add(label);
		panel.add(display);
		
		return panel;
	}
	
	public JPanel paymentType() {
		FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
		JPanel panel = new JPanel(fl);
		
		JLabel label = new JLabel("Payment ID : " + paymentId);
		
		panel.add(label);
		
		return panel;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
	
	
}
