package ru.dmitruk.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dmitruk.library.models.Book;
import ru.dmitruk.library.models.User;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
      Optional<Book> findById(Long id);
      List<Book> findAll();



 }