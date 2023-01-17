package template.payments;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.order.Order;

public class EMoneyPayments extends Payments {

	public EMoneyPayments() {
		super("E-Money");
	}
	
	public EMoneyPayments(String paymentId) {
		super("E-Money", paymentId);
	}

	@Override
	public JPanel displayPaymentDetail() {
		FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
		JPanel panel = new JPanel(fl);
		
		JLabel label = new JLabel("Please select your E-Money");
		String s[] = {"Dana", "OVO", "Gopay"};
		JComboBox cb = new JComboBox(s);
		
		panel.add(label);
		panel.add(cb);
		
		return panel;
	}

}
