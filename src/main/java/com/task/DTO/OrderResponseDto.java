package com.task.DTO;

public class OrderResponseDto {

	    private String message;
	    private String productName;
	    private int orderedQuantity;
	    private double unitPrice;
	    private double totalPrice ; 

	    public OrderResponseDto(String message, String productName, int orderedQuantity, 
	    		double unitPrice, double totalPrice) {
	        this.message = message;
	        this.productName = productName;
	        this.orderedQuantity = orderedQuantity;
	        this.unitPrice = unitPrice;
	        this.totalPrice = totalPrice;
	    }
	    public String getMessage() {
	        return message;
	    }

	    public String getProductName() {
	        return productName;
	    }

	    public int getOrderedQuantity() {
	        return orderedQuantity;
	    }

	    public double getUnitPrice() {
	        return unitPrice;
	    }

	    public double getTotalPrice() {
	        return totalPrice;
	    }
}
