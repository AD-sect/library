package ru.dmitruk.library.services;

import org.springframework.stereotype.Component;
import ru.dmitruk.library.models.Book;

import java.util.List;
import java.util.Optional;

@Component
public interface BookService {

    public Iterable<Book> getAll();
    public Optional<Book> getBookById(Long id);
    public Book createBook(Long id, Book book);
    public Book updateBookById(Long id, Book book);
}
