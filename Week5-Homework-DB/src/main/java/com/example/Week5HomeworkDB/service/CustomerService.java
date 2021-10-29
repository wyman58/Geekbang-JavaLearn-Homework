package com.example.Week5HomeworkDB.service;

import com.example.Week5HomeworkDB.entity.Customer;
import com.example.Week5HomeworkDB.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer saveCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("myFirstName");
        customer.setLastName("myLastName");
        customer.setEmail("abe@customerData.com");

        return customerRepository.save(customer);
    }

    public List<Customer> getAll(){
        List <Customer> customers = new ArrayList<>();
        List <Customer> customerList = customerRepository.findAll();
        customerList.forEach(customer -> {
                customers.add(customer);
        });
        return customers;
    }

    public boolean deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
        return true;
    }
}
