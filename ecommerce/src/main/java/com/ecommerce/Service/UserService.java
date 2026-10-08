package com.ecommerce.Service;

import com.ecommerce.Security.JwtService;
import org.springframework.stereotype.Service;

import com.ecommerce.Repository.UserRepository;
import com.ecommerce.entity.User;
import com.ecommerce.Security.JwtService;
@Service 
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public String login(String email, String password){
        User user = userRepository.findByEmail(email);

        if (user==null){
            throw new RuntimeException("Invalid email or password");
        }if (!user.getPassword().equals(password)){
            throw new RuntimeException("Invalid email or password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}
