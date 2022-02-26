package ru.dmitruk.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @Column(name = "username")
    private String name;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
}

