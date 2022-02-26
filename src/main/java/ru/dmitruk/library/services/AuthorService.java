package ru.dmitruk.library.services;

import ru.dmitruk.library.models.Author;
import ru.dmitruk.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public Iterable<Author> getAll();
    public Optional<Author> getDescriptionId(Long id);
    public Author createAuthor(Long id, Author author);
    public Author updateAuthor(Long id, Author author);
    public List<Book> findBookByAuthor(String name, String surname);
}
