package models.order;

import models.products.Product;

public class OrderDetail {
	private Product product;
	private int quantity;
	private double subTotal;

	public OrderDetail(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
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
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double calculateSubTotal() {
		subTotal = product.getPrice() * quantity;
		return subTotal;
	}
}
