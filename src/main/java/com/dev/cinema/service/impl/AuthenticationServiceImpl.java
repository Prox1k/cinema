package com.dev.cinema.service.impl;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private  UserService userService;
    @Autowired
    private  ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user != null) {
            String hashPassword = HashUtil.hashPassword(password, user.getSalt());
            if (hashPassword.equals(user.getPassword())) {
                return user;
            }
        }
        throw new AuthenticationException("Incorrect email or password");
    }

    @Override
    public User register(String email, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        User userWithCart = userService.add(newUser);
        shoppingCartService.registerNewShoppingCart(userWithCart);
        return userWithCart;
    }
}
