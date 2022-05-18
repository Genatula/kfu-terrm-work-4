package ru.kpfu.itis.genatulin.termwork.models;

import javax.persistence.*;

@Entity
@Table(name = "meeting")
public class Meeting extends Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}