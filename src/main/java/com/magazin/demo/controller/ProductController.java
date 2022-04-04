package com.magazin.demo.controller;

import com.magazin.demo.model.Product;
import com.magazin.demo.service.ProductService;
import com.magazin.demo.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Access to store products", tags = "/products")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "Find product by ID")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable int productId){
        Product productById = productService.getProductById(productId);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }
    @ApiOperation(value = "Update product by ID")
    @PutMapping()
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product savedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }
    @ApiOperation(value = "Add new product by ID")
    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        Product savedProduct = productService.addNewProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }
}
