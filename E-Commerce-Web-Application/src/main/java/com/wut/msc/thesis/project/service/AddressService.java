package com.wut.msc.thesis.project.service;

import com.wut.msc.thesis.project.bean.Address;
import com.wut.msc.thesis.project.bean.Customer;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AddressService {

     public Address addNewAddress(Address address);
     public Address updateAddress(Address address);
     public Address deleteAddress(int addressId);
     public Address getAddressById(int addressId);
     public Set<Address> getAllAddress();
     public Set<Address> getCustomerAddress(int customerId);
}
