package com.example.javatest.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.javatest.models.Customer;


@Service
@Transactional
public class CustomerService {
    
    @Autowired
    CustomerRepository repository;

    public void save(Customer customer) {
        repository.save(customer);
    }

    public Customer get(Long id) {
        return repository.findById(id).get();
    }

    public Iterable<Customer> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Customer> filter(String keyword) {
        return repository.search(keyword);
    }
}
