package ru.dmitruk.library.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmitruk.library.models.Book;
import ru.dmitruk.library.repository.BookRepository;
import ru.dmitruk.library.services.BookService;

import java.util.Optional;

@RestController
@RequestMapping(value ="/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
//    @PreAuthorize("hasAuthority('users:read')")
    public Iterable<Book> booksList(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('users:read')")
    public Optional<Book> bookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PostMapping(value = "/{id}", consumes = {"application/json;charset=UTF-8"})
//    @PreAuthorize("hasAuthority('users:read')")
    public Book createBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.createBook(id, book);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('users:read')")
    public Book updateBookById(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBookById(id, book);
    }
}
