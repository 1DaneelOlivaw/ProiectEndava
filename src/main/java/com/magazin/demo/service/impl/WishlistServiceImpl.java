package com.magazin.demo.service.impl;

import com.magazin.demo.exception.NotFoundException;
import com.magazin.demo.model.Product;
import com.magazin.demo.model.Wishlist;
import com.magazin.demo.repository.WishlistRepository;
import com.magazin.demo.service.WishlistService;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist getWishlist(int userId) {
        return wishlistRepository.findById(userId).orElseThrow(
                () -> new NotFoundException((String.format("wishlist with userId %s could not be found", userId))));
    }

    public Wishlist addProductById(int usedId, Product product) {
        Wishlist currentWishlist = getWishlist(usedId);
        Set<Product> products =  currentWishlist.getProducts();
        products.add(product);
        currentWishlist.setProducts(products);
        return wishlistRepository.save(currentWishlist);
    }

    @Override
    public Wishlist deleteProductById(int userId, Product product) {
        Wishlist currentWishlist = getWishlist(userId);
        Set<Product> products =  currentWishlist.getProducts();
        products.remove(product);
        currentWishlist.setProducts(products);
        return wishlistRepository.save(currentWishlist);
    }

    @Override
    public void updateWishlist(int user, Set<Product> products) {
        wishlistRepository.updateWishlist(user,products);
    }

    @Override
    public Wishlist saveChanges(Wishlist wishlist) {
        wishlistRepository.saveAndFlush(wishlist);
        return wishlist;
    }

}
