package com.karacsonyiz.gameshop.service;

import com.karacsonyiz.gameshop.database.BasketDao;
import com.karacsonyiz.gameshop.database.ProductDao;
import com.karacsonyiz.gameshop.model.BasketItem;
import com.karacsonyiz.gameshop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BasketService {

    private BasketDao basketDao;
    private ProductDao productDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public BasketService(BasketDao basketDao, ProductDao productDao) {
        this.basketDao = basketDao;
        this.productDao = productDao;
    }

    public Response addToBasket(BasketItem basketItem){
        List<BasketItem> sameProductInBasket = findBasketItemsByUserIdAndProductId(basketItem);
        if(sameProductInBasket.size() == 0){
            basketDao.addToBasket(basketItem);
            LOGGER.info("Product with id: " + basketItem.getProductId() + " has been added to Basket by user with id : " + basketItem.getUserId());
            return new Response(true,"Termék hozzáadva a kosárhoz.");
        } else {
            return new Response(false,"A termék már hozzá van adva a kosárhoz.");
        }
    }

    public List<Product> listBasketItemsForUser(long userId) {
        return productDao.listBasketItemsForUser(userId);
    }

    public List<BasketItem> findBasketItemsByUserIdAndProductId(BasketItem basketItem) {
        return basketDao.findBasketItemsByUserIdAndProductId(basketItem);
    }

    public Response deleteFromBasketByUserIdAndProductId(long userId, String productId) {
        basketDao.deleteFromBasketByProductIdAndUserId(userId, productId);
        LOGGER.info("Item with id: " + productId + " has been removed from Basket by user with id: " + userId);
        return new Response(true, "A termék törölve lett a kosárból.");
    }

    public Response deleteFromBasketByUserId(long userId) {
        basketDao.deleteFromBasketByUserId(userId);
        LOGGER.info("User with id : " + userId + " disposed the contents of Basket.");
        return new Response(true,"Az összes termék törölve lett a kosárból.");
    }
}
