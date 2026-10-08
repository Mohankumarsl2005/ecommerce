package com.ecommerce.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Service.UserService;
import com.ecommerce.entity.User;

import java.util.Map;

@RestController 
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping 
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        String token = userService.login(
                user.getEmail(),
                user.getPassword() );
        return Map.of("token", token); }

}
