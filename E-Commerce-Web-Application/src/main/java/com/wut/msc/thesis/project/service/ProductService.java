package com.wut.msc.thesis.project.service;

import com.wut.msc.thesis.project.bean.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product addNewProduct(Product product);
    public Product updateProduct(int  productId, Product product);
    public Product getProductByProductId(int productId);
    public List<Product> findProductByProductName(String productName);
    public List<Product> getAllProduct();
    public List<Product> findByProductNameContaining(String productName);
    public Product deleteProductById(int productId);
    public Product deleteByProductName(String productName);
    public List<Product> findProductByProductPriceBetween(int startPrice, int endPrice);
}
