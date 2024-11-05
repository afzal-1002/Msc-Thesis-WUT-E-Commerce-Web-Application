package com.wut.msc.thesis.project.service.implementation;

import com.wut.msc.thesis.project.bean.Address;
import com.wut.msc.thesis.project.bean.Customer;
import com.wut.msc.thesis.project.exceptions.AddressNotFoundException;
import com.wut.msc.thesis.project.repository.AddressRepository;
import com.wut.msc.thesis.project.repository.CustomerRepository;
import com.wut.msc.thesis.project.service.AddressService;
import com.wut.msc.thesis.project.service.CustomerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddressServiceImplementation implements AddressService {

    final CustomerService customerService;
    final AddressRepository addressRepository;
    final CustomerRepository customerRepository;
    public AddressServiceImplementation(AddressRepository addressRepository, CustomerRepository customerRepository, @Lazy CustomerService customerService) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @Override
    public Address addNewAddress(Address address) {
        return  this.addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        Optional<Address> addressOptional = this.addressRepository.findById(address.getAddressId());
        if (addressOptional.isPresent()) {
            Address addressToUpdate = addressOptional.get();
            addressToUpdate.setAddressId(address.getAddressId());
            addressToUpdate.setCity(address.getCity());
            addressToUpdate.setCountry(address.getCountry());
            addressToUpdate.setState(address.getState());
            addressToUpdate.setStreet(address.getStreet());
            addressToUpdate.setZip(address.getZip());
            addressToUpdate.setAddressType(address.getAddressType());
            return addressRepository.save(addressToUpdate);
        }else {
            throw new AddressNotFoundException("Address not found");
        }

    }

    @Override
    public Address deleteAddress(int addressId) {
        Optional<Address> addressOptional = this.addressRepository.findById(addressId);
        if (addressOptional.isPresent()) {
            this.addressRepository.deleteById(addressId);
            return addressOptional.get();
        }else {
            throw new AddressNotFoundException("Address not found");
        }
    }

    @Override
    public Address getAddressById(int addressId) {
       Optional<Address> address = this.addressRepository.findById(addressId);
       if (address.isPresent()) {
           return address.get();
       }else {
           throw new AddressNotFoundException("Address not found");
       }
    }

    @Override
    public Set<Address> getAllAddress() {
        Set<Address> addressList = new HashSet<>(this.addressRepository.findAll());
        if (addressList.isEmpty()) {
            throw new AddressNotFoundException("Address not found");
        }else {
            return addressList;
        }
    }

    @Override
    public Set<Address> getCustomerAddress(int customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        Set<Address> addressList = new HashSet<>();
        customer.ifPresent(customer1 -> addressList.addAll(new HashSet<>(customer1.getAddress())));
        return addressList;
        }



}
