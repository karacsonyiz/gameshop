package com.karacsonyiz.gameshop.controller;

import com.karacsonyiz.gameshop.model.Product;
import com.karacsonyiz.gameshop.service.ProductService;
import com.karacsonyiz.gameshop.service.Response;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/api/products", method = RequestMethod.POST)
    public Response createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
}
