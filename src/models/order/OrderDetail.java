package models.order;

import models.products.Product;

public class OrderDetail {
	private Product product;
	private int quantity;

	public OrderDetail(Product product) {
		super();
		this.product = product;
		this.quantity = 1;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void addQuantity() {
		this.quantity += 1;
	}
	
	public double calculateSubTotal() {
		double subTotal;
		
		subTotal = product.getPrice() * quantity;
		return subTotal;
	}
}
