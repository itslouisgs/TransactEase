package factories.payments;

import models.payments.CashPayment;
import models.payments.Payments;

public class CashFactories extends PaymentsFactories {

	@Override
	public Payments getPayment() {
		// TODO Auto-generated method stub
		return new CashPayment();
	}

}
