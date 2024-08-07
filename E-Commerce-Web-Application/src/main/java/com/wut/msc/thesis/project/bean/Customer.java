package com.wut.msc.thesis.project.bean;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;


    @NotNull(message = "Please provide your name: ")
    private String firstName;

    private String lastName;

    @Column(unique = true,nullable = false)
    @NotNull(message = "Please provide email id: ")
    private String email;


    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @ManyToMany()
    private List<Address> address = new ArrayList<>();

    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Cart> orders = new ArrayList<>();



}
