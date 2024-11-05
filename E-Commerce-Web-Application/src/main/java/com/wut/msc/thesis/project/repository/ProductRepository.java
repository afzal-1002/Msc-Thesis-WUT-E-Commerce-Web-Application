package com.wut.msc.thesis.project.repository;

import com.wut.msc.thesis.project.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    public List<Product> findByProductName(String productName);
    public List<Product> findByProductNameContaining(String productName);
    public List<Product> findProductByProductPriceBetween(double startingPrice, double endingPrice);
    public Product deleteByProductName(String productName);
}
