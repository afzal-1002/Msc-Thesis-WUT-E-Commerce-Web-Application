package com.wut.msc.thesis.project.controller;

import com.wut.msc.thesis.project.bean.Cart;
import com.wut.msc.thesis.project.service.CartService;
import com.wut.msc.thesis.project.service.implementation.CartServiceImplementation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CartController {

    final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    public ResponseEntity<Cart> addCart(Cart cart){
       Cart cart1 =  this.cartService.addCart(cart);
       return ResponseEntity.ok(cart1);
    }
    public ResponseEntity<Cart> removeCart(Cart cart){
        Cart cart1 =  this.cartService.removeCart(cart);
        return ResponseEntity.ok(cart1);
    }
    public ResponseEntity<Cart> getCartById(Cart cart){
        Cart cart1 =  this.cartService.getCartById(cart);
        return ResponseEntity.ok(cart1);
    }
    public ResponseEntity<List<Cart>> getCartByCustomerId(int customerId){
        List<Cart> carts =  this.cartService.getCartByCustomerId(customerId);
        return ResponseEntity.ok(carts);
    }
    public ResponseEntity<Cart> updateCart(Cart cart){
        Cart cart1 =  this.cartService.updateCart(cart);
        return ResponseEntity.ok(cart1);
    }
    public ResponseEntity<List<Cart>> getAllCart(){
        List<Cart> carts =  this.cartService.getAllCart();
        return ResponseEntity.ok(carts);
    }
    public ResponseEntity<List<Cart>> getCartByTotalPrice(Long productPrice){
        List<Cart> carts =  this.cartService.getCartByTotalPrice(productPrice);
        return ResponseEntity.ok(carts);
    }
    public ResponseEntity<List<Cart>> getCartByTotalPriceBetween(Long totalPrice1, Long totalPrice2){
        List<Cart> cartList = this.cartService.getCartByTotalPriceBetween(totalPrice1, totalPrice2);
        return  ResponseEntity.ok(cartList);
    }


}
