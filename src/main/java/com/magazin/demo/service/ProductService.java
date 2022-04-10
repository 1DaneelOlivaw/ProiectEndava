package com.magazin.demo.service;

import com.magazin.demo.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long productId);
    Product addNewProduct(Product product);
    Product updateProduct(Product product);
    Product getProductByName (String productName);
    void deleteProduct(Long product);

    void deleteProductByName(String productName);

    List<Product> findAll();
}

