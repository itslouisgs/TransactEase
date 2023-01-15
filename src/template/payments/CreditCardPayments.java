package template.payments;

import java.util.Scanner;

import models.order.Order;

public class CreditCardPayments extends Payments {

	public CreditCardPayments(String paymentName, Order orders) {
		super("Credit Card", orders);
	}

	@Override
	protected void displayPaymentDetail(Order orders) {
		Scanner scan = new Scanner(System.in);
		
		String ccProvider = "";

		do {
			System.out.println("Please select your bank [BCA | Mandiri | BNI | CIMB]:  ");
			ccProvider = scan.nextLine();

		} while (!ccProvider.equals("BCA") && !ccProvider.equals("Mandiri") && !ccProvider.equals("BNI")
				&& !ccProvider.equals("CIMB"));

		System.out.println("Order is paid with card from " + ccProvider );
		System.err.println("Total: " + orders.calculateTotal());
	}

}
