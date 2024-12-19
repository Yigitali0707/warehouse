package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "returns")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany
    private List<ReturnProduct> returnProducts=new ArrayList<>();

    @ManyToOne
    private User createdUser;

    @ManyToOne
    private Customer customer;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationTime;
}
