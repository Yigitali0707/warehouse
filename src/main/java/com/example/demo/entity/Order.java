package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany
    private List<OrderProduct> orderProducts=new ArrayList<>();

    @ManyToOne
    private User createdUser;

    @ManyToOne
    private Customer customer;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationTime;

}
