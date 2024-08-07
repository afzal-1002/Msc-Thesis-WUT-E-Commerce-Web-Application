package com.wut.msc.thesis.project.service.implementation;

import com.wut.msc.thesis.project.bean.Cart;
import com.wut.msc.thesis.project.bean.Customer;
import com.wut.msc.thesis.project.exceptions.CartNotFoundException;
import com.wut.msc.thesis.project.repository.CartRepository;
import com.wut.msc.thesis.project.service.CartService;
import com.wut.msc.thesis.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImplementation implements CartService {

    final CartRepository cartRepository;
    final CustomerService customerService;

    public CartServiceImplementation(CartRepository cartRepository, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
    }

    @Override
    public Cart addCart(Cart cart) {
        try {
            return this.cartRepository.save(cart);
        }catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override
    public Cart removeCart(Cart cart) {
        return cart;
    }

    @Override
    public Cart getCartById(Cart cart) {
        Optional<Cart> cart1 = this.cartRepository.findById(cart.getCartId());
        if (cart1.isPresent()) {
            return cart1.get();
        }else {
            throw new CartNotFoundException("Cart not found.");
        }
    }

    @Override
    public List<Cart> getCartByCustomerId(int customerId) {
        Customer customer = this.customerService.getCustomerById(customerId);
        if (customer == null) {
            throw new CartNotFoundException("Customer not found.");
        }else {
           return customer.getOrders();
        }

    }

    @Override
    public Cart updateCart(Cart cart) {
       Optional<Cart> cart1 = this.cartRepository.findById(cart.getCartId());
    if(cart1.isPresent()) {
            cart1.get().setCartId(cart.getCartId());
            cart1.get().setCustomer(cart.getCustomer());
            cart1.get().setProducts(cart.getProducts());
            cart1.get().setDescription(cart.getDescription());
            cart1.get().setTotalPrice(cart.getTotalPrice());
            return this.cartRepository.save(cart1.get());
        }else {
            throw new CartNotFoundException("Cart not found.");
        }

    }

    @Override
    public List<Cart> getAllCart() {
        List<Cart> carts = this.cartRepository.findAll();
        if (carts.isEmpty()) {
            throw new CartNotFoundException("Cart not found.");
        }else{
            return carts;
        }
    }

    @Override
    public List<Cart> getCartByTotalPrice(Long totalPrice) {
        List<Cart> cartList = this.cartRepository.getCartByTotalPrice(totalPrice);
        if (cartList.isEmpty()) {
            throw new CartNotFoundException("No Cart found for total price.");
        }else {
            return cartList;
        }
    }

    @Override
    public List<Cart> getCartByTotalPriceBetween(Long totalPrice1, Long totalPrice2){
        List<Cart> cartList = this.cartRepository.getCartByTotalPriceBetween(totalPrice1,totalPrice2);
        if (cartList.isEmpty()) {
            throw new CartNotFoundException("No Cart found between " + totalPrice1 + " and " + totalPrice2 + " price.");
        }else {
            return cartList;
        }
    }
}
