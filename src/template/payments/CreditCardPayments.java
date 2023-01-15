package template.payments;

import java.awt.FlowLayout;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.order.Order;

public class CreditCardPayments extends Payments {

	public CreditCardPayments() {
		super("Credit Card");
	}

	@Override
	public JPanel displayPaymentDetail() {
		FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
		JPanel panel = new JPanel(fl);
		
		JLabel label = new JLabel("Please select your bank");
		String s[] = {"BCA", "Mandiri", "BNI", "CIMB"};
		JComboBox cb = new JComboBox(s);
		
		panel.add(label);
		panel.add(cb);
		
		return panel;
	}

}
