package ru.kpfu.itis.genatulin.termwork.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "meeting")
@SequenceGenerator(name = "default_gen", sequenceName = "seq_meeting", allocationSize = 1)
public class Meeting extends Event {

    @ManyToMany
    @JoinTable(name = "meetings_participants",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> participants = new LinkedHashSet<>();

    @Override
    public String getUrlPath() {
        return "meetings";
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}