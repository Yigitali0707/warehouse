package com.example.demo.repo;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE %:find% OR c.lastName LIKE %:find% OR c.phoneNumber LIKE %:find%")
    List<Customer> findByFirstNameOrLastNameOrPhoneNumber(@Param("find") String find);

}