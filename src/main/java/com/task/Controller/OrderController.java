package com.task.Controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.task.DTO.OrderResponseDto;
import com.task.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	
	 @Autowired
	    private OrderService service;

	    @PostMapping("/buy/{productId}/{qty}")
	    public OrderResponseDto buy(@PathVariable Long productId,
	                      @PathVariable int qty,
	                      Principal principal) {

	    	if (principal == null) {
	    		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please login");
	          //  throw new RuntimeException("Unauthorized: Please login first");
	        }
	    	return service.buy(principal.getName(), productId, qty);
	      
	    }

}
