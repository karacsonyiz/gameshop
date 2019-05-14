package com.karacsonyiz.gameshop.service;

import com.karacsonyiz.gameshop.model.Product;
import com.karacsonyiz.gameshop.database.ProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> listProducts() {
        return productDao.listProducts();
    }

    public Optional<Product> findProductById(String id) {
            Optional<Product> product = productDao.findGameById(id);
            if(!product.isPresent()){
                LOGGER.warn("Failed to retrieve product with id: " + id);
            }
        return product;
    }
}
