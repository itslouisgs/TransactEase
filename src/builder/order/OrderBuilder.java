package builder.order;

import models.order.Order;
import models.payments.Payments;

public class OrderBuilder {
	private String title, type;
	private int price;
	private Payments payments;
	
	public OrderBuilder setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public OrderBuilder setType(String type) {
		this.type = type;
		return this;
	}
	
	public OrderBuilder setPrice(int price) {
		this.price = price;
		return this;
	}
	
	public OrderBuilder setPayments(Payments payments) {
		this.payments = payments;
		return this;
	}
	
	public Order build() {
		return new Order(title,type,price,payments);
	}
}
