package com.wut.msc.thesis.project.repository;

import com.wut.msc.thesis.project.bean.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    public List<Cart> getCartByTotalPrice(Long totalPrice);
    public List<Cart> getCartByTotalPriceBetween(Long totalPrice1, Long totalPrice2);
}
