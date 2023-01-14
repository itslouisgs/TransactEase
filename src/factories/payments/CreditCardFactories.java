package factories.payments;

import models.payments.CreditCardPayment;
import models.payments.Payments;

public class CreditCardFactories extends PaymentsFactories {

	@Override
	public Payments getPayment() {
		return new CreditCardPayment();
	}

}
