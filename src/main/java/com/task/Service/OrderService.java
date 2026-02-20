package com.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.DTO.OrderResponseDto;
import com.task.Entity.Consumer;
import com.task.Entity.Order;
import com.task.Entity.Product;
import com.task.Repository.ConsumerRepo;
import com.task.Repository.OrderRepo;
import com.task.Repository.ProductRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

	@Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ConsumerRepo consumerRepo;
   
    public OrderResponseDto  buy(String username, Long productId, int qty) {

        Consumer consumer = consumerRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Consumer not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < qty) {
        	throw new RuntimeException("Stock not available");
        }

        product.setQuantity(product.getQuantity() - qty);
        productRepo.save(product);

        double orderTotalPrice = product.getUnitPrice() * qty;
        
        Order order = new Order();
        order.setConsumer(consumer);
        order.setProduct(product);
        order.setProductName(product.getName());
        order.setQuantity(qty);
		order.setTotalPrice(orderTotalPrice);
        orderRepo.save(order);

        return new OrderResponseDto(
                "Order placed successfully",
                product.getName(),
                qty,
                product.getUnitPrice(),
                orderTotalPrice
        );
    }

}
