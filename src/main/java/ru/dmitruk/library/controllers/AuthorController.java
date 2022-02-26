package ru.dmitruk.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dmitruk.library.models.Author;
import ru.dmitruk.library.models.Book;
import ru.dmitruk.library.repository.AuthorRepository;
import ru.dmitruk.library.repository.BookRepository;
import ru.dmitruk.library.services.AuthorService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping()
//    @PreAuthorize("hasAuthority('users:read')")
    public Iterable<Author> getAll(){
        return authorService.getAll();
    }

    @GetMapping("/authors/{id}")
//    @PreAuthorize("hasAuthority('users:read')")
    public Optional<Author> getDescriptionById(@PathVariable Long id){
        return authorService.getDescriptionId(id);
    }

    @PostMapping(value ="/authors/{id}", consumes = {"application/xml","application/json"})
//    @PreAuthorize("hasAuthority('users:read')")
    public Author createAuthor(@PathVariable Long id, @RequestBody Author author) {
        return authorService.createAuthor(id, author);
    }

    @PutMapping("/authors/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author){
        return authorService.updateAuthor(id, author);
    }

    @GetMapping("/findbook")
    public List<Book> findBookByAuthor(@RequestParam(name ="name", required = false) String name, @RequestParam(name = "surname", required = false) String surname){
        return authorService.findBookByAuthor(name, surname);
    }




}
