package com.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.DTO.DeleteResponse;
import com.task.Entity.Product;
import com.task.Repository.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
    private ProductRepo repo;

    public Product add(Product p) {
        return repo.save(p);
    }

    public DeleteResponse  delete(Long id) {
    	Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        repo.delete(product); // delete the product
        
        return new DeleteResponse("Deleted Stock", product);
    }
    
    public Product updateById(Long id, Product p) {
        return repo.findById(id)
                .map(existingProduct -> {
                    // Increment the quantity
                	existingProduct.setName(p.getName());
                    existingProduct.setQuantity(existingProduct.getQuantity() + p.getQuantity());
                    existingProduct.setUnitPrice(p.getUnitPrice());
                    existingProduct.setDescription(p.getDescription());
                    existingProduct.setBrand(p.getBrand());
                    existingProduct.setCategory(p.getCategory());
                    existingProduct.setImage(p.getImage());
                    existingProduct.setWeight(p.getWeight());
                    existingProduct.setDimensions(p.getDimensions());
                    existingProduct.setRating(p.getRating());
                   
                    return repo.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }


    public Product get(Long id) {
        return repo.findById(id).orElseThrow();
    }

}
