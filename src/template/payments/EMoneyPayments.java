package template.payments;

import java.util.Scanner;

import models.order.Order;

public class EMoneyPayments extends Payments {

	public EMoneyPayments(Order orders) {
		super("E-Money", orders);
	}

	@Override
	protected void displayPaymentDetail(Order orders) {
		Scanner scan = new Scanner(System.in);
		String eMoney = "";

		do {
			System.out.println("Please select your E-Money [DANA | OVO | GOPAY]:  ");
			eMoney = scan.nextLine();

		} while (!eMoney.equals("DANA") && !eMoney.equals("OVO") && !eMoney.equals("GOPAY"));

		System.out.println("Order is paid with " + eMoney);
		System.out.println("Total: " + orders.calculateTotal());
	}

}
