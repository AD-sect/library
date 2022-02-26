package ru.dmitruk.library.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name="authors")
public class Author {
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Author author = (Author) o;
//        return Objects.equals(id, author.id) && Objects.equals(author_name, author.author_name) && Objects.equals(author_surname, author.author_surname) && Objects.equals(description, author.description) && Objects.equals(books, author.books);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, author_name, author_surname, description, books);
//    }



    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authors_id")
    private Long id;

    @Column(name = "author_name")
    private String author_name;

    @Column(name = "author_surname")
    private String author_surname;

    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @ManyToMany(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "authors_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id"))
//    @JsonManagedReference
    private List<Book> books;





}
