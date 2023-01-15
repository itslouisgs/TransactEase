package template.payments;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import models.order.Order;

public class CashPayments extends Payments {

	public CashPayments() {
		super("Cash");
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel displayPaymentDetail() {
		FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
		JPanel panel = new JPanel(fl);
		
		JLabel label = new JLabel("Cash selected");
		
		panel.add(label);
		
		return panel;
	}

}
