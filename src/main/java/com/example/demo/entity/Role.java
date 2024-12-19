package com.example.demo.entity;

import com.example.demo.entity.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;


import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName; // e.g., "ROLE_USER", "ROLE_ADMIN"

    public Role(RoleName roleName) {
        this.roleName = roleName.toString();
    }
}

