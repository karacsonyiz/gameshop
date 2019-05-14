package com.karacsonyiz.gameshop.controller;

import com.karacsonyiz.gameshop.model.Product;
import com.karacsonyiz.gameshop.service.ProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    public List<Product> listProducts(){
        return productService.listProducts();
    }

    @RequestMapping(value = "/api/products/{id}", method = RequestMethod.GET)
    public Optional<Product> findProductById(@PathVariable String id){
        return productService.findProductById(id);
    }
}
