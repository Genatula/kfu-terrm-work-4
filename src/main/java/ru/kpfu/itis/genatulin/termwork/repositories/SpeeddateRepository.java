package ru.kpfu.itis.genatulin.termwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.genatulin.termwork.models.Speeddate;

public interface SpeeddateRepository extends JpaRepository<Speeddate, Long> {
}