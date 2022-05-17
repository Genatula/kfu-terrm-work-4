package ru.kpfu.itis.genatulin.termwork.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.genatulin.termwork.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     boolean existsByEmail(String email);
     boolean existsByUsername(String username);
     User getUserByUsername(String username);
     void deleteUserByUsername(String username);
     @Query("select u from User u where u.username = :#{ principal.username }")
     User getCurrentUser();
}