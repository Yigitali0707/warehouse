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
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;


    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

}

