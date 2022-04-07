package com.magazin.demo.controller;

import com.magazin.demo.model.Product;
import com.magazin.demo.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Api(value = "Access to store products", tags = "/products")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @ApiOperation(value = "Find all products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @ApiOperation(value = "Find product by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Product not found") })
    @GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable int productId){
        Product productById = productService.getProductById(productId);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }

    @ApiOperation(value = "Update product by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Product not found") })
    @PutMapping(path = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product product) {
        Objects.requireNonNull(product);
        Product savedProduct = productService.getProductById(productId);
        product.setId(savedProduct.getId());// ca sa nu pot modifica id-ul
        productService.updateProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }
    @ApiOperation(value = "Add new product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New product added to the database"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        Objects.requireNonNull(product);
        Product savedProduct = productService.addNewProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete product by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Product removed from the database"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Product not found") })
    @DeleteMapping("/{productId}") //TODO zice ca sterge si daca nu gaseste id-ul
    public ResponseEntity<Product> deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
