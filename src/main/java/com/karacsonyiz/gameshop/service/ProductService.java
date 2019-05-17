package com.karacsonyiz.gameshop.service;

import com.karacsonyiz.gameshop.model.Product;
import com.karacsonyiz.gameshop.database.ProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    public Response createProduct(Product product) {
             return productValidator(product);
    }

    private Response productValidator(Product product){
        Optional<Product> existingProduct = productDao.findGameById(product.getProductId());
        if(existingProduct.isPresent()){
            LOGGER.warn("The product with this ID already exists. Try a lottery today! :)");
            return new Response(false,"Termék ilyen id-val már létezik,menjél lottózni. :)");
        }
        if(productDao.listProducts().contains(product)){
            LOGGER.warn("A product tried to be created with a name that already exists : " + product.getName());
            return new Response(false,"Termék ilyen névvel már létezik.");
        }
        if(product.getName().equals("") || product.getProducer().equals("") || product.getPrice() < 1 || product.getQuantity() < 1) {
            return new Response(false,"Valamelyik érték érvénytelen.");
        }
        productDao.createProduct(product);
        LOGGER.info("Product created with name: " + product.getName() + "and sajt : " + product.getProducer()) ;
        return new Response(true,"Termék létrehozása sikerült!");
    }
}
