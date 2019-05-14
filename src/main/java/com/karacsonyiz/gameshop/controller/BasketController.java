package com.karacsonyiz.gameshop.controller;

import com.karacsonyiz.gameshop.model.BasketItem;
import com.karacsonyiz.gameshop.model.Product;
import com.karacsonyiz.gameshop.model.User;
import com.karacsonyiz.gameshop.service.BasketService;
import com.karacsonyiz.gameshop.service.Response;
import com.karacsonyiz.gameshop.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class BasketController {

    private BasketService basketService;
    private UserService userService;

    public BasketController(BasketService basketService, UserService userService) {
        this.basketService = basketService;
        this.userService = userService;
    }

    @RequestMapping(value = "api/basket/{productId}", method = RequestMethod.POST)
    public Response addToBasket(@PathVariable String productId) {
        User user = getAuthenticatedUser();
        if(user != null) {
            return basketService.addToBasket(new BasketItem(user.getId(),productId));
        }
        return new Response(false,"A termék kosárba rakásához jelentkezz be!");
    }

    @RequestMapping(value = "/api/basket", method = RequestMethod.GET)
    public List<Product> listBasketItemsForUser() {
        User user = getAuthenticatedUser();
        if(user != null) {
            return basketService.listBasketItemsForUser(user.getId());
        } else {
            return Collections.emptyList();
            //return new Response(false,"User is not authenticated. What are you doing here?"); -- multiple return values?
        }
    }

    @RequestMapping(value = "/api/basket/{productId}", method = RequestMethod.DELETE)
    public Response deleteSingleProduct(@PathVariable String productId) {
        User user = getAuthenticatedUser();
        if (user != null) {
            return basketService.deleteFromBasketByUserIdAndProductId(user.getId(), productId);
        } else {
            return new Response(false, "A felhasználó nem jogosult a törlésre.");
        }
    }

    @RequestMapping(value = "/api/basket", method = RequestMethod.DELETE)
    public Response deleteWholeBasket() {
        User user = getAuthenticatedUser();
        if(user != null){
            return basketService.deleteFromBasketByUserId(user.getId());
        } else {
            return new Response(false,"A felhasználó nem jogosult a törlésre.");
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


