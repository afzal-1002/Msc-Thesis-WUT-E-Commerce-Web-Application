package com.wut.msc.thesis.project.service.implementation;

import com.wut.msc.thesis.project.repository.CartRepository;
import com.wut.msc.thesis.project.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementationBuilder {
    private CartRepository cartRepository;
    private CustomerService customerService;

    public CartServiceImplementationBuilder setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        return this;
    }

    public CartServiceImplementationBuilder setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
        return this;
    }

    public CartServiceImplementation createCartServiceImplementation() {
        return new CartServiceImplementation(cartRepository, customerService);
    }
}