package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.RoleName;
import com.example.demo.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;



import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

//    private final RoleRepository roleRepository;
//    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        Role role1=new Role(RoleName.ROLE_ADMIN);
//        Role role2=new Role(RoleName.ROLE_CASHIER);
//        roleRepository.save(role1);
//        roleRepository.save(role2);
//
//        User user1=User.builder()
//                .firstName("Yigitali")
//                .lastName("Dalaboyev")
//                .age(23)
//                .phoneNumber("900242571")
//                .username("Yigitali")
//                .password(passwordEncoder.encode("123"))
//                .roles(List.of(role1,role2))
//                .build();
//         User user2=User.builder()
//                .firstName("Yigitali")
//                .lastName("Dalaboyev")
//                .age(23)
//                .phoneNumber("900242571")
//                .username("123")
//                .password(passwordEncoder.encode("123"))
//                .roles(List.of(role2))
//                .build();
//        userRepository.save(user1);
//        userRepository.save(user2);

        Category category1 = categoryRepository.save(Category.builder().name("Category1").build());
        Category category2 = categoryRepository.save(Category.builder().name("Category2").build());
        productRepository.save(Product.builder().category(category1).name("Product1").quantity(0L).reservedQuantity(0L).build());
        productRepository.save(Product.builder().category(category2).name("Product2").quantity(0L).reservedQuantity(0L).build());

        Customer customer= Customer.builder()
                .firstName("Yigitali")
                .lastName("Dalaboyev")
                .age(23)
                .phoneNumber("900242571")
                .build();
        customerRepository.save(customer);
    }
}
