package com.wut.msc.thesis.project.bean;

import com.wut.msc.thesis.project.enums.ProductCategories.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @ElementCollection
    @CollectionTable(name = "product_subcategories", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "subcategory_type")
    @Column(name = "subcategory_value")
    private Map<String, String> subcategories;

    private int productQuantity;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Cart> carts = new ArrayList<>();


}






