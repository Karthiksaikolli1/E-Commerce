package com.task.Controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task.Entity.Consumer;
import com.task.Service.ConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
    private ConsumerService service;
	
	 
    @PostMapping("/register")
    public Consumer register(@RequestBody Consumer consumer) {
     return service.register(consumer);		
}

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Consumer consumer) {
    String token = service.login(consumer.getUsername(), consumer.getPassword());
    return Map.of("token", token);
}

}

