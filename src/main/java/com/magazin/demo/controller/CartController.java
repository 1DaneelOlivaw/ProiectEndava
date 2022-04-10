package com.magazin.demo.controller;

import com.magazin.demo.model.Cart;
import com.magazin.demo.model.Order;
import com.magazin.demo.model.Product;
import com.magazin.demo.model.User;
import com.magazin.demo.service.*;
import io.swagger.annotations.*;
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
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;

    @ApiOperation(value = "Find cart by username", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cart found!"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @GetMapping("/{username}")
    public ResponseEntity<Cart> getCart(@PathVariable String username) {
        Cart cartById = cartService.getUserCart(username).orElseThrow();
        return new ResponseEntity<>(cartById, HttpStatus.OK);
    }

    private Cart getInitialCart(String username) {
        if (cartService.getUserCart(username).isPresent()) {//cartService.existsCartForUser(userId){
            log.info("am gasit");
            return cartService.getUserCart(username).get();
        } else {
            log.info("nu am gasit");
            return new Cart(userService.getUser(username), new HashSet<>(), 0f, 0);
        }
    }

    @ApiOperation(value = "Add a product to the cart", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New product added to the cart"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @PutMapping("/{username}/{productName}")
    public ResponseEntity<Cart> addProductByName(@PathVariable String username, @PathVariable String productName) {
        log.info("Buna seara");
        Product newProduct = productService.getProductByName(productName);
        if (!newProduct.getStock())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//("Product out of stock");
        log.info("am gasit produsul dorit");
        Cart initialCart = getInitialCart(username);
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
        return new ResponseEntity<>(cartService.getUserCart(username).orElseThrow(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product from the cart", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses({
            @ApiResponse(code = 204, message = "Product removed from the wishlist"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Product not found") })
    @DeleteMapping("/{username}/{productName}")
    public ResponseEntity<Cart> deleteProductByName(@PathVariable String username, @PathVariable String productName) {
        if (cartService.getUserCart(username).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cart initialCart = cartService.getUserCart(username).get();
        Product newProduct = productService.getProductByName(productName);

        Set<Product> productSet = initialCart.getCartProducts();
        if (!productSet.contains(newProduct)) {
            log.info("nu am gasit produsul in cos");
            return new ResponseEntity<>(cartService.getUserCart(username).get(), HttpStatus.NOT_FOUND);
        }
        log.info("am gasit produsul");
        productSet.remove(newProduct);
        initialCart.setCartProducts(productSet);
        int total = initialCart.getTotalItems();
        log.info("am aflat numarul initial de produse din cos");
        initialCart.setTotalItems(total-1);
        log.info("am modificat numarul de elemente din cos");
        Float initialTotalPrice = initialCart.getTotalPrice();
        initialCart.setTotalPrice(initialTotalPrice-newProduct.getPrice());
        cartService.saveChanges(initialCart);
        return new ResponseEntity<>(cartService.getUserCart(username).get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new order based on cart", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses({
            @ApiResponse(code = 201, message = "Order placed!"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Couldn't place ordeer!") })
    @PutMapping("/{username}")
    public ResponseEntity<Cart> saveCartAsOrder(@PathVariable String username){
        if (cartService.getUserCart(username).isEmpty()) {
            log.info("cart not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cart cart = cartService.getUserCart(username).get();
        log.info("cart found");
        User currentCustomer = userService.getUser(username);
        log.info("customer found");
        Order order = new Order(new Timestamp(System.currentTimeMillis()),currentCustomer,"pending");
        //order.setOrderProducts(cart.getCartProducts());
        Set<Product> products = cart.getCartProducts();
        Boolean issue = false;
        for(Product p : products) {
            if (!p.getStock()) {
                issue = true;
                break;
            }
        }
        if (issue)
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        orderService.saveOrder(order);
        log.info("order created");
        cartService.deleteCart(cart);
        log.info("cart removed");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

