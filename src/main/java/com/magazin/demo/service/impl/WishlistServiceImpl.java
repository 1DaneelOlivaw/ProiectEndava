package com.magazin.demo.service.impl;

import com.magazin.demo.exception.NotFoundException;
import com.magazin.demo.model.Product;
import com.magazin.demo.model.User;
import com.magazin.demo.model.Wishlist;
import com.magazin.demo.repository.UserRepository;
import com.magazin.demo.repository.WishlistRepository;
import com.magazin.demo.service.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Wishlist getWishlist(String username) {
        User user = userRepository.findByUsername(username);
        return wishlistRepository.getWishlistByUsername(user.getId()).orElseThrow(
                () -> new NotFoundException((String.format("wishlist for user %s could not be found", username))));
    }

    public Wishlist addProductByName(String username, Product product) {
        Wishlist currentWishlist = getWishlist(username);
        Set<Product> products =  currentWishlist.getProducts();
        products.add(product);
        currentWishlist.setProducts(products);
        return wishlistRepository.save(currentWishlist);
    }

    @Override
    public Wishlist deleteProductByName(String username, Product product) {
        Wishlist currentWishlist = getWishlist(username);
        Set<Product> products =  currentWishlist.getProducts();
        products.remove(product);
        currentWishlist.setProducts(products);
        return wishlistRepository.save(currentWishlist);
    }

    @Override
    public void updateWishlist(String username, Set<Product> products) {
        User user = userRepository.findByUsername(username);
        wishlistRepository.updateWishlist(user.getId(),products);
    }

    @Override
    public Wishlist saveChanges(Wishlist wishlist) {
        wishlistRepository.saveAndFlush(wishlist);
        return wishlist;
    }

    @Override
    public List<Wishlist> findAll() {
        return wishlistRepository.findAll();
    }
}
