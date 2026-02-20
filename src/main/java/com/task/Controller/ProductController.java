package com.task.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.task.DTO.DeleteResponse;
import com.task.DTO.ProductResponseDto;
import com.task.Entity.Product;
import com.task.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
    private ProductService service;

    @PostMapping("/add")
    public ProductResponseDto  add(
    		@RequestParam("name") String name,
            @RequestParam("unitPrice") double unitPrice,
            @RequestParam("quantity") int quantity,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "weight", required = false) String weight,
            @RequestParam(value = "dimensions", required = false) String dimensions,
            @RequestParam(value = "rating", required = false, defaultValue = "0") double rating,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws Exception {

    	Product product = new Product();
        product.setName(name);
        product.setUnitPrice(unitPrice);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setBrand(brand);
        product.setCategory(category);
        product.setWeight(weight);
        product.setDimensions(dimensions);
        product.setRating(rating);
        
        
        if (file != null && !file.isEmpty()) {
            product.setImage(file.getBytes());
            product.setImageType(file.getContentType()); // FIX: set image type
        }
  
        
//		@ModelAttribute Product product, 
//		@RequestParam(value = "file", required = false) MultipartFile image) throws Exception {
//    	if (image!= null && !image.isEmpty()) {
//            product.setImage(image.getBytes());
//        }
    	Product savedProduct = service.add(product);

        // Return DTO with URL to image
        return new ProductResponseDto(savedProduct);
        //return service.add(product);
    }

    @PutMapping("/update/{id}")
    public ProductResponseDto update(@PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("unitPrice") double unitPrice,
            @RequestParam("quantity") int quantity,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "weight", required = false) String weight,
            @RequestParam(value = "dimensions", required = false) String dimensions,
            @RequestParam(value = "rating", required = false, defaultValue = "0") double rating,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws Exception {
    	Product product = new Product();
        product.setName(name);
        product.setUnitPrice(unitPrice);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setBrand(brand);
        product.setCategory(category);
        product.setWeight(weight);
        product.setDimensions(dimensions);
        product.setRating(rating);
//    		@PathVariable Long id,
//    		@ModelAttribute Product product,
//            @RequestParam(value = "image", required = false) MultipartFile image) throws Exception {

//        if (image != null && !image.isEmpty()) {
//            product.setImage(image.getBytes());
//}
        if (file != null && !file.isEmpty()) {
            product.setImage(file.getBytes());
            product.setImageType(file.getContentType());
        }

        //return service.updateById(id, product);
        Product updatedProduct = service.updateById(id, product);
        return new ProductResponseDto(updatedProduct);
        
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse  delete(@PathVariable Long id) {
    	return service.delete(id);
        
    }

    @GetMapping("/get/{id}")
    public ProductResponseDto  get(@PathVariable Long id) {
    	Product product = service.get(id);
        return new ProductResponseDto(product);
        //return service.get(id);
    }
    
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Product product = service.get(id);

        if (product == null || product.getImage() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
        		 product.getImageType() != null && product.getImageType().equals("image/png")
                 ? MediaType.IMAGE_PNG
                 : MediaType.IMAGE_JPEG
 );
        		//MediaType.IMAGE_JPEG); // or IMAGE_PNG depending on your file
        headers.setContentLength(product.getImage().length);

        return new ResponseEntity<>(product.getImage(), headers, HttpStatus.OK);
    }
}
