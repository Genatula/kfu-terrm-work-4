package ru.kpfu.itis.genatulin.termwork.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "speeddate")
@SequenceGenerator(name = "default_gen", sequenceName = "seq_speeddate", allocationSize = 1)
public class Speeddate extends Event {
    @ManyToMany
    @JoinTable(name = "speeddates_participants",
            joinColumns = @JoinColumn(name = "speeddate_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> participants = new LinkedHashSet<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "target", nullable = false)
    private Target target;

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    @Override
    public String getUrlPath() {
        return "speeddates";
    }
}