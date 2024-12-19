package com.example.demo.entity;

import com.example.demo.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Supply supply;

    @OneToOne
    private Product product;

    private Integer count;
}
