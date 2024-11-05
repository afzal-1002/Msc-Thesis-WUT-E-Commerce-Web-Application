package com.wut.msc.thesis.project.service.implementation;

import com.wut.msc.thesis.project.bean.Product;
import com.wut.msc.thesis.project.exceptions.ProductExistException;
import com.wut.msc.thesis.project.exceptions.ProductNotFoundException;
import com.wut.msc.thesis.project.repository.ProductRepository;
import com.wut.msc.thesis.project.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService  {

    final ProductRepository  productRepository;

    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addNewProduct(Product product) {
        Product product1 = this.getProductByProductId(product.getProductId());
        if (product1 != null) {
            return this.productRepository.save(product);
        }else {
            throw new ProductExistException("Product with Id " + product.getProductId() + " already exists");
        }
    }

    @Override
    public Product updateProduct(int productId,Product product) {
        Optional<Product> product1 = this.productRepository.findById(productId);
        if(product1.isPresent()){
            Product productToUpdate = product1.get();
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setProductDescription(product.getProductDescription());
            productToUpdate.setProductPrice(product.getProductPrice());
            productToUpdate.setProductQuantity(product.getProductQuantity());
            productToUpdate.setProductCategory(product.getProductCategory());
            return this.productRepository.save(productToUpdate);
        }else {
            throw new ProductNotFoundException("Product with Id " + product.getProductId() + " does not exist");
        }
    }


    @Override
    public Product getProductByProductId(int productId) {
        Optional<Product> product1 = this.productRepository.findById(productId);
        if(product1.isPresent()){
            return product1.orElse(null);
        }else {
            throw new ProductNotFoundException("Product with Id " + productId + " does not exist");
        }
    }

    @Override
    public List<Product> findProductByProductName(String productName) {
       List<Product> productList = this.productRepository.findByProductName(productName);
       if(productList.isEmpty()){
           throw new ProductNotFoundException("Product with Name " + productName + " does not exist");
       }else {
          return productList;
       }
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = this.productRepository.findAll();
        if(products.isEmpty()){
            throw new ProductNotFoundException("No products found");
        }else {
           return products;
        }
    }


    @Override
    public List<Product> findByProductNameContaining(String productName) {
        List<Product> products = this.productRepository.findByProductNameContaining(productName).stream().toList();
        if(products.isEmpty()){
            throw new ProductNotFoundException("No products found");
        }else {
            return products;
        }
    }

    @Override
    public Product deleteProductById(int productId)  {
        Product product1 = this.getProductByProductId(productId);
        if (product1 != null) {
            this.productRepository.deleteById(productId);
            return product1;
        }else {
            throw new ProductNotFoundException("Product with Id " + productId + " does not exist");
        }

    }

    @Override
    public Product deleteByProductName(String productName) {
        List<Product> product = this.productRepository.findByProductName(productName);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with Name " + productName + " does not exist");
        }else {
            return this.productRepository.deleteByProductName(productName);
        }
    }

    @Override
    public List<Product> findProductByProductPriceBetween(int startPrice, int endPrice) {
        List<Product> products = this.productRepository.findProductByProductPriceBetween(startPrice, endPrice).stream().toList();
        if(products.isEmpty()){
            throw new ProductNotFoundException("No products found between " + startPrice + " and " + endPrice + " prices");
        }else {
            return products;
        }
    }
}
