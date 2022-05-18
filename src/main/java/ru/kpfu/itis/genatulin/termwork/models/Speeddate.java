package ru.kpfu.itis.genatulin.termwork.models;

import javax.persistence.*;

@Entity
@Table(name = "speeddate")
@SequenceGenerator(name = "default_gen", sequenceName = "seq_speeddate", allocationSize = 1)
public class Speeddate extends Event {
    @Enumerated(EnumType.STRING)
    @Column(name = "target", nullable = false)
    private Target target;

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    @Override
    public String getUrlPath() {
        return "speeddates";
    }
}