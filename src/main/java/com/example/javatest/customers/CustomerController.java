package com.example.javatest.customers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.javatest.models.Customer;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @RequestMapping("/")
    public String root(Map<String, Object> model) {
        Iterable<Customer> customers = service.findAll();
        model.put("customers", customers);
        return "index";
    }

    @PostMapping("/create")
    public String createCustomerSave(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String address,
                                     Map<String, Object> model) {

        Customer customer = new Customer(name, email, address);
        service.save(customer);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editCustomerForm(@RequestParam Long id,
                                   Map<String, Object> model) {
        
        Customer customer = service.get(id);
        model.put("customer", customer);
        return "edit_customer";
    }

    @PostMapping("/edit")
    public String editCustomerSave(@RequestParam Long id,
                                   @RequestParam String name,
                                   @RequestParam String email,
                                   @RequestParam String address) {

        Customer customer = service.get(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        service.save(customer);
        return "redirect:/";
    }
    
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam Long id,
                                 Map<String, Object> model) {

        service.delete(id);
        return "redirect:/";
    }

    @PostMapping("/filter")
    public String filterCustomers(@RequestParam String keyword,
                                  Map<String, Object> model) {

        if (keyword != null && !keyword.isEmpty()) {
            Iterable<Customer> customers = service.filter(keyword);
            model.put("customers", customers);
            return "index";
        }
        return "redirect:/"; 
    }
}
