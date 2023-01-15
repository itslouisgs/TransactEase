package template.payments;

import models.order.Order;

public class CashPayments extends Payments {

	public CashPayments(Order orders) {
		super("Cash", orders);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void displayPaymentDetail(Order orders) {
		// TODO Auto-generated method stub
		System.out.println("Order is paid with cash");
		System.out.println("Total: " + orders.calculateTotal());
	}

}
