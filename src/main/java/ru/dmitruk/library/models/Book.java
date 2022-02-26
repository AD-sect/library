package ru.dmitruk.library.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name="books")
public class Book {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "books_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "books")
    @JsonBackReference
    private  List<Author> authors;

}