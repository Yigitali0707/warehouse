package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "supply")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany
    private List<SupplyProduct> supplyProducts=new ArrayList<>();

    @ManyToOne
    private User createdUser;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationTime;


}
