package com.co.veterinariagian.demo.controller.v2;


import com.co.veterinariagian.demo.model.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*
@RestController
public class JWTController {

    @PostMapping("user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        String token = getJWTToken(username);
        User user = new User(username,token);
        user.setUser(username);
        user.setToken(token);
        return user;

    }
    public String generateToken(){
        return JwtClaimsSet.builder().
    }
}*/