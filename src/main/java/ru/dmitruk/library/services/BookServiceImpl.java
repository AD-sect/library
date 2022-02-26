package ru.dmitruk.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmitruk.library.models.Book;
import ru.dmitruk.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;
@Service("bookServiceImpl")
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book createBook(Long id, Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBookById(Long id, Book book) {
        return bookRepository.findById(id).map(
                b -> {
                    b.setTitle(book.getTitle());
                    b.setDescription(book.getDescription());
                    return bookRepository.save(b);
                }).orElse(null);
    }
}
