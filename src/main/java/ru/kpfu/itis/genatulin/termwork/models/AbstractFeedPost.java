package ru.kpfu.itis.genatulin.termwork.models;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractFeedPost implements Captionable, Describable, HavingDate {
    @Column(name = "caption", nullable = false, length = 64)
    private String caption;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String description) {
        this.shortDescription = description;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public abstract String getUrlPath();
}
