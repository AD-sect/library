package ru.dmitruk.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dmitruk.library.models.Author;
import ru.dmitruk.library.models.Book;
import ru.dmitruk.library.repository.AuthorRepository;
import ru.dmitruk.library.repository.BookRepository;

import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;


    @GetMapping()
    @PreAuthorize("hasAuthority('users:read')")
    public Iterable<Author> getAll(){
        Iterable<Author> authors = authorRepository.findAll();
//        model.addAttribute("books", books);
        return authors;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public Optional<Author> getDescriptionById(@PathVariable Long id){
        Optional<Author> author = authorRepository.findById(id);
        return  author;
    }
}
