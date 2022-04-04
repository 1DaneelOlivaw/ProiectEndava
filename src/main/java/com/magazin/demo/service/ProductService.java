package com.magazin.demo.service;

import com.magazin.demo.model.Product;

public interface ProductService {
    Product getProductById(int productId);
    Product addNewProduct(Product product);
    Product updateProduct(Product product);

}

