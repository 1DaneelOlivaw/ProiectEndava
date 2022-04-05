package com.magazin.demo.service.impl;

import com.magazin.demo.exception.NotFoundException;
import com.magazin.demo.model.Product;
import com.magazin.demo.repository.ProductRepository;
import com.magazin.demo.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

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
    public Product getProductById (int productId){
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
        savedProduct.setPrice(Optional.ofNullable(product.getPrice()).orElse(savedProduct.getPrice()));

        return productRepository.save(savedProduct);
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteProduct(productId);

       /* Product currentProduct = getProductById(productId);
        Set<Product> products = currentProduct.getProducts();
        products.remove(product);
        currentProduct.setProducts(products);
        return ProductRepository.save(currentProduct);*/
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

