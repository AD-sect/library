package ru.dmitruk.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.dmitruk.library.models.User;

import java.util.Optional;
import java.util.jar.JarEntry;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = :username")
    public Optional<User> getUserByUsername(@Param("username") String username);

}
