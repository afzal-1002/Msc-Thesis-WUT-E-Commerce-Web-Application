package com.wut.msc.thesis.project.controller;


import com.wut.msc.thesis.project.bean.Address;
import com.wut.msc.thesis.project.bean.Customer;
import com.wut.msc.thesis.project.service.AddressService;
import com.wut.msc.thesis.project.service.CustomerService;
import com.wut.msc.thesis.project.service.implementation.CustomerServiceImplementation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/e-commerce")
public class CustomerController {
    final
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addNewCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws Exception {
        Customer customer1 = this.customerService.addCustomer(customer);
        return ResponseEntity.ok(customer1);
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        Customer customer1 = this.customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(customer1);
    }

    @GetMapping(value = "/getAllCustomers", produces = "application/json")
    public ResponseEntity<ArrayList<Customer>> getAllCustomers() {
        ArrayList<Customer> customers = this.customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String>  deleteCustomer(@PathVariable int id){
      this.customerService.deleteCustomerById(id);
      return new ResponseEntity<>("Customer deleted", HttpStatus.OK);
    }

    @GetMapping("/getCustomerByEmail/{email}")
    public ResponseEntity<Customer>  getCustomerByEmail(@PathVariable String email){
      Customer  customer =  this.customerService.getCustomerByEmail(email);
      return ResponseEntity.ok(customer);
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Customer>  getCustomerById(@PathVariable int id){
        Customer customer =  this.customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/getCustomerByPhone/{customerPhoneNumber}")
    public ResponseEntity<Customer>  getCustomerByPhoneNumber(@PathVariable String customerPhoneNumber){
        Customer customer1 = this.customerService.getCustomerByPhoneNumber(customerPhoneNumber);
        return ResponseEntity.ok(customer1);
    }

    @PutMapping("/updateCustomerAddress/customerId={customerId}/addressId={addressId}")
    public ResponseEntity<Customer>  updateCustomerAddress(@PathVariable int addressId,  @PathVariable int customerId){
        Customer customer1 = this.customerService.updateCustomerAddress(addressId, customerId);
        return ResponseEntity.ok(customer1);
    }


}
