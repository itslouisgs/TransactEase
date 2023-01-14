package models.payments;

public class CreditCardPayment extends Payments {
	private String ccType;
	
	public CreditCardPayment() {
		this.setPaymentType("Credit Card");
		this.setCcType(ccType);
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}
	
	
	

}
