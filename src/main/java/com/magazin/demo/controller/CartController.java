package com.magazin.demo.controller;

import com.magazin.demo.model.Cart;
import com.magazin.demo.model.Customer;
import com.magazin.demo.model.Order;
import com.magazin.demo.model.Product;
import com.magazin.demo.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Api(value = "Access to customer cart", tags = "/cart")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cart")
public class CartController {
    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    private final ProductService productService;
    private final CustomerService customerService;
    private final CartService cartService;
    private final OrderService orderService;

    @ApiOperation(value = "Find cart by userID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cart found!"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable int userId) {
        Cart cartById = cartService.getUserCart(userId).orElseThrow();
        return new ResponseEntity<>(cartById, HttpStatus.OK);
    }

    private Cart getInitialCart(int userId) {
        if (cartService.getUserCart(userId).isPresent()) {//cartService.existsCartForUser(userId){
            log.info("am gasit");
            return cartService.getUserCart(userId).get();
        } else {
            log.info("nu am gasit");
            return new Cart(customerService.getCustomer(userId).orElseThrow(), new HashSet<>(), 0f, 0);
        }
    }

    @ApiOperation(value = "Add a product to the cart")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New product added to the cart"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @PutMapping("/{userId}/{productName}")
    public ResponseEntity<Cart> addProductByName(@PathVariable int userId, @PathVariable String productName) {
        log.info("Buna seara");
        Product newProduct = productService.getProductByName(productName);
        if (!newProduct.getStock())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//("Product out of stock");
        log.info("am gasit produsul dorit");
        Cart initialCart = getInitialCart(userId);
        log.info("am gasit cosul utilizatorului");
        Set<Product> productsInCart = initialCart.getCartProducts();
        log.info("am aflat ce produse sunt deja in cos");
        productsInCart.add(newProduct);
        log.info("am adaugat pe lista noul produs");
        initialCart.setCartProducts(productsInCart);
        log.info("am adaugat in cos noul produs");
        int total = initialCart.getTotalItems();
        log.info("am aflat numarul initial de produse din cos");
        initialCart.setTotalItems(total+1);
        log.info("am modificat numarul de elemente din cos");
        Float initialTotalPrice = initialCart.getTotalPrice();
        log.info("am aflat pretul total initial al produselor din cos: "+initialTotalPrice);
        Float newProductPrice = newProduct.getPrice();
        log.info("noul produs costa: "+newProductPrice);
        Float finalPrice = initialTotalPrice+newProductPrice;
        log.info("pretul total va fi: "+finalPrice);
        initialCart.setTotalPrice(finalPrice);
        log.info("am adaugat la pretul total si pretul noului produs introdus in cos");
        cartService.saveChanges(initialCart);
        log.info("am salvat modificarile");
        return new ResponseEntity<>(cartService.getUserCart(userId).orElseThrow(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product from the cart")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Product removed from the wishlist"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Product not found") })
    @DeleteMapping("/{userId}/{productName}")
    public ResponseEntity<Cart> deleteProductByName(@PathVariable int userId, @PathVariable String productName) {
        if (cartService.getUserCart(userId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cart initialCart = cartService.getUserCart(userId).get();
        Product newProduct = productService.getProductByName(productName);

        Set<Product> productSet = initialCart.getCartProducts();
        if (!productSet.contains(newProduct)) {
            log.info("nu am gasit produsul in cos");
            return new ResponseEntity<>(cartService.getUserCart(userId).get(), HttpStatus.NOT_FOUND);
        }
        log.info("am gasit produsul");
        productSet.remove(newProduct);//TODO acum pot sa adaug un produs de 5 ori, dar de sters merge o singura data
        initialCart.setCartProducts(productSet);
        int total = initialCart.getTotalItems();
        log.info("am aflat numarul initial de produse din cos");
        initialCart.setTotalItems(total-1);
        log.info("am modificat numarul de elemente din cos");
        Float initialTotalPrice = initialCart.getTotalPrice();
        initialCart.setTotalPrice(initialTotalPrice-newProduct.getPrice());
        cartService.saveChanges(initialCart);
        return new ResponseEntity<>(cartService.getUserCart(userId).get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new order based on cart")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order placed!"), //TODO actualizeaza codurile
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Product not found") })
    @PutMapping("/{userId}")
    public ResponseEntity<Cart> saveCartAsOrder(@PathVariable int userId){
        if (cartService.getUserCart(userId).isEmpty()) {
            log.info("cart not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cart cart = cartService.getUserCart(userId).get();
        log.info("cart found");
        Customer currentCustomer = customerService.getCustomer(userId).orElseThrow();
        log.info("customer found");
        Order order = new Order(new Timestamp(System.currentTimeMillis()),currentCustomer,"pending");
        //TODO adauga si produsele, rezolva conflictele
        //order.setOrderProducts(cart.getCartProducts());
        orderService.saveOrder(order);
        log.info("order created");
        cartService.deleteCart(cart);
        log.info("cart removed");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
