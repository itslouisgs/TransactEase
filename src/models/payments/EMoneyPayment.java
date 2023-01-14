package models.payments;

public class EMoneyPayment extends Payments{
	private String eMoneyProvider;
	
	public EMoneyPayment() {
		this.setPaymentType("E-Money");
		this.seteMoneyProvider(eMoneyProvider);
	}

	public String geteMoneyProvider() {
		return eMoneyProvider;
	}

	public void seteMoneyProvider(String eMoneyProvider) {
		this.eMoneyProvider = eMoneyProvider;
	}
	
	

}
