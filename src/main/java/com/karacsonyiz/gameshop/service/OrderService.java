package com.karacsonyiz.gameshop.service;

import com.karacsonyiz.gameshop.database.OrderDao;
import com.karacsonyiz.gameshop.model.Order;
import com.karacsonyiz.gameshop.model.OrderItem;
import com.karacsonyiz.gameshop.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    private OrderDao orderDao;
    private BasketService basketService;

    public OrderService(OrderDao orderDao,BasketService basketService) {
        this.orderDao = orderDao;
        this.basketService = basketService;
    }

    public Response createOrderAndOrderItems(long userId) {
        List<Product> productList = basketService.listBasketItemsForUser(userId);
        if(!productList.isEmpty()) {
            orderDao.createOrderAndOrderItems(userId);
            return new Response(true,"Sikeres rendelés!");
        } else {
            return new Response(false,"A kosár üres!");
        }
    }

    public List<Order> listOrders() {
        return orderDao.listOrders();
    }

    public List<Order> listOrdersByUserId(long userId) {
        return orderDao.listOrdersByUserId(userId);
    }

    public List<OrderItem> listOrderItems(long userId, long orderId) {
        return orderDao.listOrderItems(userId, orderId);
    }
}
