package ru.kpfu.itis.genatulin.termwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.genatulin.termwork.models.Meeting;
import ru.kpfu.itis.genatulin.termwork.models.User;

import java.util.Set;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}