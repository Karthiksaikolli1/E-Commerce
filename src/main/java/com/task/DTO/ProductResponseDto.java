package com.task.DTO;

import java.util.Base64;

import com.task.Entity.Product;

public class ProductResponseDto {


	    private Long id;
	    private String name;
	    private double unitPrice;
	    private int quantity;
	    private double totalPrice;
	    private String description;
	    private String brand;
	    private String category;
	    private String weight;
	    private String dimensions;
	    private double rating;
	    private String image; // Base64 image

	    public ProductResponseDto(Product product) {
	        this.id = product.getId();
	        this.name = product.getName();
	        this.unitPrice = product.getUnitPrice();
	        this.quantity = product.getQuantity();
	        this.totalPrice = product.getTotalPrice();
	        this.description = product.getDescription();
	        this.brand = product.getBrand();
	        this.category = product.getCategory();
	        this.weight = product.getWeight();
	        this.dimensions = product.getDimensions();
	        this.rating = product.getRating();
	        
	        
	        if (product.getImage() != null && product.getImageType() != null) {
	            this.image = "data:" + product.getImageType() + ";base64," +
	                         Base64.getEncoder().encodeToString(product.getImage());
	        } else {
	            this.image = null;
	        }

	        //this.image = "/product/image/" + product.getId(); // URL to GET image
//	        if (product.getImage() != null) {
//	            this.image = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage());
//	        } else {
//	            this.image = null;
//	        }
	    }

	    
	 // getters and setters
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(double unitPrice) {
			this.unitPrice = unitPrice;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
		}

		public String getDimensions() {
			return dimensions;
		}

		public void setDimensions(String dimensions) {
			this.dimensions = dimensions;
		}

		public double getRating() {
			return rating;
		}

		public void setRating(double rating) {
			this.rating = rating;
		}


		public String getImage() {
			return image;
		}


		public void setImage(String image) {
			this.image = image;
		}

		

	   	    
	
}
