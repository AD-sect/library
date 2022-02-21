package ru.dmitruk.library.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authors_id")
    private Long id;

    @Column(name = "author_name")
    private String author_name;

    @Column(name = "author_surname")
    private String author_surname;

    @Column(name = "description")
    private String description;

    @ManyToMany(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "authors_books",
            joinColumns = @JoinColumn(name = "authors_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id"))
//    @JsonManagedReference
    private List<Author> books;

    public String getFullName(){
        return getAuthor_name() + getAuthor_surname();
    }



}
