package com.wut.msc.thesis.project.controller;

import com.wut.msc.thesis.project.bean.Address;
import com.wut.msc.thesis.project.bean.Customer;
import com.wut.msc.thesis.project.service.AddressService;
import com.wut.msc.thesis.project.service.implementation.AddressServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/e-commerce")
public class AddressController {


    final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @PostMapping("/addNewAddress")
    public ResponseEntity<Address> addNewAddress(@RequestBody Address address){
        Address address1 = this.addressService.addNewAddress(address);
        return new ResponseEntity<>(address1, HttpStatus.OK);
    }

    @PutMapping("/updateAddress/{address}")
    public ResponseEntity<Address>  updateAddress(@PathVariable Address address){
        Address address1 = this.addressService.updateAddress(address);
        return new ResponseEntity<>(address1, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress/{addressId}")
    public ResponseEntity<Address>  deleteAddress(@PathVariable int addressId){
        Address address1 = this.addressService.deleteAddress(addressId);
        return new ResponseEntity<>(address1, HttpStatus.OK);
    }

    @GetMapping("/getAddressById/{addressId}")
    public ResponseEntity<Address>  getAddressById(@PathVariable int addressId){
        Address address1 = this.addressService.getAddressById(addressId);
        return new ResponseEntity<>(address1, HttpStatus.OK);
    }

    @GetMapping("/getAllAddress")
    public ResponseEntity<Set<Address>> getAllAddress(){
        Set<Address> addressList = this.addressService.getAllAddress();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/getAddressByCustomerId/{customerId}")
    public ResponseEntity<Set<Address>> getCustomerAddress(@PathVariable int customerId){
        Set<Address> addresses =  this.addressService.getCustomerAddress(customerId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

}
