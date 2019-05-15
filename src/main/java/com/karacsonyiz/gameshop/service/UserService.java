package com.karacsonyiz.gameshop.service;

import com.karacsonyiz.gameshop.database.UserDao;
import com.karacsonyiz.gameshop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public Optional<User> findUserByUserName(String name) {
        Optional<User> user = userDao.findUserByUserName(name);
        if(!user.isPresent()){
            LOGGER.warn("Failed to retrieve user with name: " + name);
        }
        return user;
    }

    public Response createUser(User user) {
        if (userDao.findUserByUserName(user.getName()).isPresent() || userDao.findUserByEmail(user.getEmail()).isPresent()) {
            return new Response(false,"A felhasználónév vagy jelszó foglalt.");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        long generatedId = userDao.createUser(user);
        LOGGER.info("A new user has been created with id : " + generatedId);
        return new Response(true,"Sikeres regisztráció!");
    }
}
