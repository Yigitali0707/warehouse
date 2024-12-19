package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public Page<Customer> getAllCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer addCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        return customerRepository.save(customer);
    }

    public List<Customer> findCustomer(String find) {
       return customerRepository.findByFirstNameOrLastNameOrPhoneNumber(find);
    }

    public Customer updateCustomer(UUID id, String firstName, String lastName, Integer age, String phoneNumber) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer Not Found!"));
        if(firstName!=null){
            customer.setFirstName(firstName);
        }
        if(lastName!=null){
            customer.setLastName(lastName);
        }
        if(age!=null){
            customer.setAge(age);
        }
        if(phoneNumber!=null){
            customer.setPhoneNumber(phoneNumber);
        }
        return customerRepository.save(customer);
    }
}
