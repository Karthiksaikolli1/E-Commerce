package com.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Entity.Consumer;
import com.task.Repository.ConsumerRepo;
import com.task.Security.JwtUtil;

@Service
public class ConsumerService {

	@Autowired
    private ConsumerRepo consumerRepo;
    
    @Autowired
    private JwtUtil jwtUtil;


    public Consumer register(Consumer consumer) {
             return consumerRepo.save(consumer);
    }

    public String login(String username, String password) {
      Consumer consumer = consumerRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Consumer not found"));

        if (!consumer.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(username, consumer.getRole());
    }

}
