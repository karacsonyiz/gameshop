package com.karacsonyiz.gameshop.controller;

import com.karacsonyiz.gameshop.model.Order;
import com.karacsonyiz.gameshop.model.OrderItem;
import com.karacsonyiz.gameshop.model.Product;
import com.karacsonyiz.gameshop.model.User;
import com.karacsonyiz.gameshop.service.BasketService;
import com.karacsonyiz.gameshop.service.OrderService;
import com.karacsonyiz.gameshop.service.Response;
import com.karacsonyiz.gameshop.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class OrderController {

    private OrderService orderService;
    private UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @RequestMapping(value = "/api/myorders", method = RequestMethod.POST)
    public Response createOrderAndOrderItems() {
        User user = getAuthenticatedUser();
        if(user != null) {
            orderService.createOrderAndOrderItems(user.getId());
            return new Response(true,"A rendelés sikeres!");
        } else {
            return new Response(false,"A felhasználó nincs bejelentkezve!");
        }
    }

    @RequestMapping(value = "/api/myorders", method = RequestMethod.GET)
    public List<Order> listOrdersByUserId() {
        User user = getAuthenticatedUser();
        if(user != null) {
            return orderService.listOrdersByUserId(user.getId());
        } else {
            return Collections.emptyList();
        }
    }

    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public List<Order> listOrders() {
            return orderService.listOrders();
    }

    @RequestMapping(value = "api/myorderitems/{orderId}", method = RequestMethod.GET )
    public List<OrderItem> listOrderItems(@PathVariable long orderId) {
        User user = getAuthenticatedUser();
        if(user != null){
            return orderService.listOrderItems(user.getId(),orderId);
        } else {
            return Collections.emptyList();
        }
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        User user = userService.findUserByUserName(authentication.getName()).get();
        return user;
    }
}
