package ru.kpfu.itis.genatulin.termwork.models;

import javax.persistence.*;

@Entity
@Table(name = "meeting")
@SequenceGenerator(name = "default_gen", sequenceName = "seq_meeting", allocationSize = 1)
public class Meeting extends Event {

    @Override
    public String getUrlPath() {
        return "meetings";
    }
}