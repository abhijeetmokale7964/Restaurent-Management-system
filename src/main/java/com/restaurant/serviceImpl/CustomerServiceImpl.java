package com.restaurant.serviceImpl;

import com.restaurant.entity.Customer;
import com.restaurant.repository.CustomerRepository;
import com.restaurant.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    // Save customer
    @Override
    public Customer saveCustomer(Customer customer) {
        logger.info("Saving new customer: {}", customer.getName());
        return customerRepository.save(customer);
    }

    // Get all customers
    @Override
    public List<Customer> getAllCustomers() {
        logger.info("Fetching all customers");
        return customerRepository.findAll();
    }

    // Get customer by ID
    @Override
    public Customer getCustomerById(Long id) {

        logger.info("Fetching customer with id: {}", id);

        Optional<Customer> customer = customerRepository.findById(id);

        return customer.orElse(null);
    }

    // Update customer
    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {

        logger.info("Updating customer with id: {}", id);

        Optional<Customer> existing = customerRepository.findById(id);

        if (existing.isPresent()) {

            Customer customer = existing.get();

            customer.setName(updatedCustomer.getName());
            customer.setPhone(updatedCustomer.getPhone());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAddress(updatedCustomer.getAddress());

            return customerRepository.save(customer);
        }

        logger.warn("Customer not found with id: {}", id);
        return null;
    }

    // Delete customer
    @Override
    public void deleteCustomer(Long id) {

        logger.info("Deleting customer with id: {}", id);

        customerRepository.deleteById(id);
    }
}