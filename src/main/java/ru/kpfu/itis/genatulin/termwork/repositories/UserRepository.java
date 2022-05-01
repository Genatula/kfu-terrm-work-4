package ru.kpfu.itis.genatulin.termwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.genatulin.termwork.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}