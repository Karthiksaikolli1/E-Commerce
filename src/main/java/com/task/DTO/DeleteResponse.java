package com.task.DTO;

import com.task.Entity.Product;

public class DeleteResponse {

	private String message;
    private Product product;

    public DeleteResponse(String message, Product product) {
        this.message = message;
        this.product = product;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
