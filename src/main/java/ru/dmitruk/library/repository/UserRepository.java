package ru.dmitruk.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dmitruk.library.models.User;

import java.util.Optional;
import java.util.jar.JarEntry;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
