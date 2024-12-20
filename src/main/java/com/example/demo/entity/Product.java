package com.example.demo.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Category category;
    private String name;
    private String barcode;
    private Long quantity=0L;
    private Long reservedQuantity=0L;
    private byte[] photo;
    @OneToOne
    private User createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationTime;


}
