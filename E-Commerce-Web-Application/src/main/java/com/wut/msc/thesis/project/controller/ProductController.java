package com.wut.msc.thesis.project.controller;


import com.wut.msc.thesis.project.bean.Product;
import com.wut.msc.thesis.project.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/e-commerce")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addNewProduct")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product)
    {
       Product product1 = this.productService.addNewProduct(product);
       return ResponseEntity.ok(product1);
    }

    @PutMapping("/updateProduct/productId/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product product)
    {
        Product updateProduct = this.productService.updateProduct(productId, product);
        return ResponseEntity.ok(updateProduct);
    }
    @GetMapping("/getProductById/{productId}")
    public ResponseEntity<Product> getProductByProductId(@PathVariable int productId) {
        return ResponseEntity.ok(this.productService.getProductByProductId(productId));
    }

    @GetMapping("/findProductByName/{productName}")
    public ResponseEntity<List<Product>> findProductByProductName(@PathVariable String productName) {
        return ResponseEntity.ok(this.productService.findProductByProductName(productName));
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(this.productService.getAllProduct());
    }

    @GetMapping("/getProductNameContaining/{productName}")
    public ResponseEntity<List<Product>> findByProductNameContaining(@PathVariable String productName) {
        return ResponseEntity.ok(this.productService.findByProductNameContaining(productName));
    }

    @DeleteMapping("/deleteProductById/{productId}")
    public ResponseEntity<Product> deleteProductById(@PathVariable int productId) {
        return ResponseEntity.ok(this.productService.deleteProductById(productId));
    }

    @DeleteMapping("/deleteProductByName/{productName}")
    public ResponseEntity<Product> deleteByProductName(@PathVariable String productName) {
        return ResponseEntity.ok(this.productService.deleteByProductName(productName));
    }

    @GetMapping("/findProductByProductPriceBetween/{startPrice}/{endPrice}")
    public ResponseEntity<List<Product>> findProductByProductPriceBetween(@PathVariable int startPrice, @PathVariable int endPrice)
    {
        return ResponseEntity.ok(this.productService.findProductByProductPriceBetween(startPrice, endPrice));
    }

}
