package builder;

import models.products.Product;

public class ProductBuilder {
	private Product product;

	public ProductBuilder() {
		this.product = new Product();
	}
	
	public ProductBuilder setId(String id) {
		this.product.setId(id);
		return this;
	}

	public ProductBuilder setName(String name) {
		this.product.setName(name);
		return this;
	}

	public ProductBuilder setPrice(int price) {
		this.product.setPrice(price);
		return this;
	}

	public ProductBuilder setStock(int stock) {
		this.product.setStock(stock);
		return this;
	}
	
	public Product build() {
		return this.product;
	}
}
