package com.magazin.demo.service.impl;

import com.magazin.demo.exception.NotFoundException;
import com.magazin.demo.model.Product;
import com.magazin.demo.repository.ProductRepository;
import com.magazin.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById (int productId){
        return productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException((String.format(" product with id %s could not be found", productId))));
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
    public void deleteProduct(Product product) {
        productRepository.delete(product);

       /* Product currentProduct = getProductById(productId);
        Set<Product> products = currentProduct.getProducts();
        products.remove(product);
        currentProduct.setProducts(products);
        return ProductRepository.save(currentProduct);*/
    }
}

