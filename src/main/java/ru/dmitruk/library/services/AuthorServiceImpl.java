package ru.dmitruk.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmitruk.library.models.Author;
import ru.dmitruk.library.models.Book;
import ru.dmitruk.library.repository.AuthorRepository;
import ru.dmitruk.library.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


// TODO  Найти все книги по части имени автора /findbooks?{sometext},
//  где sometext используется для LIKE поиска %sometext% по авторам.
//  Сделать через entityManager и createQuery.

@Service("authorServiceImpl")
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Author> getAll() {
       return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getDescriptionId(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author createAuthor(Long id, Author author) {
        author.setId(id);
        if (!author.getBooks().isEmpty()) {
            author.getBooks().stream()
                    .filter(book -> bookRepository.existsById(book.getId())== true)
                    .peek(book -> {
                        Optional<Book> existedBook = bookRepository.findById(book.getId());
                        book.setTitle(existedBook.get().getTitle());
                        book.setDescription((existedBook.get().getDescription()));
                    });
            List<Book> books = author.getBooks().stream()
                    .filter(book -> bookRepository.existsById(book.getId()) == false)
                    .peek(book -> {
                        bookRepository.save(book);
                    }).collect(Collectors.toList());
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        author.setId(id);
        Optional<Author> updatedAuthor = authorRepository.findById(author.getId());
        if(updatedAuthor.isPresent()){
            updatedAuthor.get().setAuthor_name(author.getAuthor_name());
            updatedAuthor.get().setAuthor_surname(author.getAuthor_surname());
            updatedAuthor.get().setDescription(author.getDescription());

            if (!author.getBooks().isEmpty()) {
                List<Book> books = author.getBooks().stream()
                        .filter(book -> bookRepository.existsById(book.getId()) == false)
                        .peek(book -> {
                            bookRepository.save(book);
                        }).collect(Collectors.toList());
            }

            updatedAuthor.get().setBooks(author.getBooks());
        }

        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public List<Book> findBookByAuthor(String name, String surname) {
        TypedQuery<Author> query;
        System.out.println(name + " " + surname);

        if(name == null && surname == null){
            return null;
        }else{
            if((name != null) || (surname == null)){

                query = entityManager.createQuery("SELECT a FROM Author a WHERE a.author_name" +
                                                    " LIKE CONCAT('%',:nameAuthor,'%')", Author.class);
                query.setParameter("nameAuthor", name);
            }else{
                if((name == null) || (surname != null)){
                    query = entityManager.createQuery("SELECT a FROM Author a WHERE a.author_surname " +
                                                         "LIKE CONCAT('%',:surnameAuthor,'%')", Author.class);
                    query.setParameter("surnameAuthor", surname);
                }else{
                    query = entityManager.createQuery("SELECT a FROM Author a WHERE a.author_name LIKE CONCAT('%',:nameAuthor,'%')" +
                                                         " AND a.author_surname LIKE CONCAT('%',:surnameAuthor,'%')", Author.class);
                    query.setParameter("nameAuthor", name);
                    query.setParameter("surnameAuthor", surname);
                }
            }

        }
        return  query.getResultList().stream().
                        map(author -> author.getBooks())
                        .flatMap(list -> list.stream())
                                .collect(Collectors.toList());

    }

}
