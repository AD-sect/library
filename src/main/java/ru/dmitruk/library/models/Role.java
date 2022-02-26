package ru.dmitruk.library.models;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "roles_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String name;

}
