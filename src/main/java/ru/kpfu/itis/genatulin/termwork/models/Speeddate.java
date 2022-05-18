package ru.kpfu.itis.genatulin.termwork.models;

import javax.persistence.*;

@Entity
@Table(name = "speeddate")
public class Speeddate extends Event {
    @Enumerated(EnumType.STRING)
    @Column(name = "target", nullable = false)
    private Target target;

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

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}