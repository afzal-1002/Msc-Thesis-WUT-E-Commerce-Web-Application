package com.wut.msc.thesis.project.service.implementation;

import com.wut.msc.thesis.project.bean.Address;
import com.wut.msc.thesis.project.bean.Customer;
import com.wut.msc.thesis.project.exceptions.*;
import com.wut.msc.thesis.project.repository.AddressRepository;
import com.wut.msc.thesis.project.repository.CustomerRepository;
import com.wut.msc.thesis.project.service.AddressService;
import com.wut.msc.thesis.project.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImplementation implements CustomerService {
    final
    CustomerRepository customerRepository;
    final
    AddressRepository addressRepository;


    public CustomerServiceImplementation(CustomerRepository customerRepository, AddressRepository addressRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Customer addCustomer(Customer customer) throws Exception {
        // Before Saving the Customer Object We need to Check if Already Exist
        Optional<Customer> customerWithEmail = customerRepository.findByEmail((String) customer.getEmail());
        Optional<Customer> customerWithPhone = customerRepository.findByPhoneNumber((String) customer.getPhoneNumber());
        if (customerWithEmail.isPresent()) {
            throw new CustomerExistException("Customer with Email " + customer.getEmail() + " already exists");
        } else if (customerWithPhone.isPresent()) {
            throw new CustomerExistException("Customer with Phone Number " + customer.getPhoneNumber() + " already exists");
        } else {
            List<Address> address = customer.getAddress();
            if (!address.isEmpty()) {
                this.addressRepository.saveAll(address);
            }
            return this.customerRepository.save(customer);
        }
    }

    @Override
    public Customer updateCustomer(int customerId, Customer customer) {
        Optional<Customer> customer1 = this.customerRepository.findById(customerId);
        if (customer1.isPresent()) {
            customer.setCustomerId(customer.getCustomerId());
            customer.setFirstName(customer.getFirstName());
            customer.setLastName(customer.getLastName());
            customer.setAddress(customer.getAddress());
            customer.setEmail(customer.getEmail());
            customer.setPhoneNumber(customer.getPhoneNumber());
            customer.setPassword(customer.getPassword());
            return this.customerRepository.save(customer);
        }else {
            throw new HandleNotFoundException("User with id " + customer.getCustomerId() + " does not exist");
        }

    }

    @Override
    public void deleteCustomerById(int customerId) {
        Optional<Customer> customer1 = this.customerRepository.findById(customerId);
         if (customer1.isPresent()) {
             this.customerRepository.deleteById(customerId);
         }else {
             throw new CustomerExistException("User with id " + customerId + " does not exist");
         }
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return (ArrayList<Customer>) this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customers = this.customerRepository.findByEmail(email);
        return customers.orElse(null);
    }

    @Override
    public Customer getCustomerById(int customer_Id) {
        Optional<Customer> customer = this.customerRepository.findById(customer_Id);
        return customer.orElse(null);
    }

    @Override
    public Customer getCustomerByPhoneNumber(String customerPhoneNumber) {
        Optional<Customer> customer1 =  this.customerRepository.findByPhoneNumber(customerPhoneNumber);
        return customer1.orElse(null);
    }

    @Override
    public Customer updateCustomerAddress(int addressId, int customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        Optional<Address> address = this.addressRepository.findById(addressId);
        if (customer.isPresent()) {
            if (address.isPresent()) {
                List<Address> addressList =  customer.get().getAddress();
                addressList.add(address.get());
                customer.get().setAddress(addressList);
                return this.customerRepository.save(customer.get());
            }else {
                throw new AddressNotFoundException("Address not found");
            }

        }else{
            throw new CustomerNotFoundException("Customer not found with Id " + customerId );
        }
    }
}
