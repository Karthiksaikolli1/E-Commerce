package com.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Entity.Admin;
import com.task.Repository.AdminRepo;
import com.task.Security.JwtUtil;

@Service
public class AdminService {

	 @Autowired
	    private AdminRepo adminRepo;

	    @Autowired
	    private JwtUtil jwtUtil;

	    public Admin register(Admin admin) {
	        return adminRepo.save(admin);
	    }

	    public String login(String username, String password) {
	        Admin admin = adminRepo.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("Admin not found"));
	        if (!admin.getPassword().equals(password))
	            throw new RuntimeException("Invalid password");

	        return jwtUtil.generateToken(username, admin.getRole());
	    }
}
