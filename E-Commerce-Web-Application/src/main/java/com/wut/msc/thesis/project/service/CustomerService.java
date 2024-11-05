package com.wut.msc.thesis.project.service;

import com.wut.msc.thesis.project.bean.Address;
import com.wut.msc.thesis.project.bean.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CustomerService {
    public Customer addCustomer(Customer customer) throws Exception;
    public Customer updateCustomer(int customerId, Customer customer);
    public void deleteCustomerById(int customerId);
    public ArrayList<Customer> getAllCustomers();
    public Customer getCustomerByEmail(String email);
    public Customer getCustomerById(int id);
    public Customer getCustomerByPhoneNumber(String customerPhoneNumber);
    public Customer updateCustomerAddress(int customerId, int addressId);
}
