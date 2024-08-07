package com.wut.msc.thesis.project.bean;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  cartId;

    @ManyToOne()
    private Customer customer;

    @ManyToMany()
    private List<Product> products = new ArrayList<>();

    private Long totalPrice;

    private String description;


}
