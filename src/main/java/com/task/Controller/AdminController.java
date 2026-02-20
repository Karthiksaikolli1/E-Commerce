package com.task.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.Entity.Admin;
import com.task.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    private AdminService service;

    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin) {
        return service.register(admin);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Admin admin) {
        String token = service.login(admin.getUsername(), admin.getPassword());
        return Map.of("token", token);
    }
}
