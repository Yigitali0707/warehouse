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
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Order order;

    @OneToOne
    private Product product;

    private Integer count;

    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

}
