package ru.dmitruk.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dmitruk.library.models.Author;
import ru.dmitruk.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findById(Long id);
    List<Author> findAll();

}
