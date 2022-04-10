package com.magazin.demo.service.impl;

import com.magazin.demo.exception.NotFoundException;
import com.magazin.demo.model.Product;
import com.magazin.demo.repository.ProductRepository;
import com.magazin.demo.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById (Long productId){
        return productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException((String.format("error message: product with id %s could not be found", productId))));
    }

    @Override
    public Product addNewProduct (Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product updateProduct (Product product) {
        Product savedProduct = getProductById(product.getId());

        savedProduct.setName(Optional.ofNullable(product.getName()).orElse(savedProduct.getName()));
        savedProduct.setPrice(Optional.ofNullable(product.getPrice()).orElse(savedProduct.getPrice()));
        savedProduct.setStock(Optional.ofNullable(product.getStock()).orElse(savedProduct.getStock()));

        return productRepository.save(savedProduct);
    }

    @Override
    public Product getProductByName(String productName) {

        return productRepository.getProductByName(productName).orElseThrow(
                () -> new NotFoundException((String.format("error message: product with name %s could not be found", productName))));
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteProduct(productId);
    }

    @Override
    public void deleteProductByName(String productName) {
        productRepository.deleteProductByName(productName);
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

