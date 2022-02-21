package ru.dmitruk.library.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmitruk.library.models.Book;
import ru.dmitruk.library.repository.BookRepository;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    @PreAuthorize("hasAuthority('users:read')")
    public Iterable<Book> booksList(){
        Iterable<Book>  books = bookRepository.findAll();
        return books;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public Optional<Book> bookById(@PathVariable Long id){
        Optional<Book> book = bookRepository.findById(id);
        return book;
    }

    @PostMapping("/{id}")
//    @PreAuthorize("hasAuthority('users:read')")
    public Book createBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public Book updateBookByTitle(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> exist = bookRepository.findById(book.getId());
        if(exist != null){
            return bookRepository.save(book);
        }else{
            return null;
        }
    }
}
