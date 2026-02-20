package com.task.Security;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.task.Repository.AdminRepo;
import com.task.Repository.ConsumerRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private AdminRepo adminRepo;
	
    @Autowired
    private ConsumerRepo consumerRepo;

    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        //  Try admin first
        return adminRepo.findByUsername(username)
          .map(admin -> new org.springframework.security.core.userdetails.User(
           admin.getUsername(),
           admin.getPassword(),
          List.of(new SimpleGrantedAuthority(admin.getRole())))) // ADMIN
            
                // If not admin, try consumer
            .orElseGet(() ->
             consumerRepo.findByUsername(username)
            .map(consumer -> new org.springframework.security.core.userdetails.User(
            consumer.getUsername(),
              consumer.getPassword(),
            List.of(new SimpleGrantedAuthority(consumer.getRole())) // CONSUMER
               ))
          .orElseThrow(() -> new UsernameNotFoundException("User not found"))
          );
    }
}
  

