package com.wut.msc.thesis.project.service;

import com.wut.msc.thesis.project.bean.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    public Cart addCart(Cart cart);
    public Cart removeCart(Cart cart);
    public Cart getCartById(Cart cart);
    public List<Cart> getCartByCustomerId(int customerId);
    public Cart updateCart(Cart cart);
    public List<Cart> getAllCart();
    public List<Cart> getCartByTotalPrice(Long productPrice);
    public List<Cart> getCartByTotalPriceBetween(Long totalPrice1, Long totalPrice2);

}
