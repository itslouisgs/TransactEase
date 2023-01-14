package factories.payments;

import models.payments.EMoneyPayment;
import models.payments.Payments;

public class EMoneyFactories extends PaymentsFactories {

	@Override
	public Payments getPayment() {
		// TODO Auto-generated method stub
		return new EMoneyPayment();
	}

}
