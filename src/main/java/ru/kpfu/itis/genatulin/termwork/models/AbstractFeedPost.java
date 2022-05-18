package ru.kpfu.itis.genatulin.termwork.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    public void setDate(Date date) {
        this.creationDate = date;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    public void setDescription(String description) {
        this.shortDescription = description;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
