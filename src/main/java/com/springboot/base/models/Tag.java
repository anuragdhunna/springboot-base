package com.springboot.base.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int tagId;

    @Column(length = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    private TagStatus isActive;

    private Timestamp createdOn;


    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TagStatus getIsActive() {
        return isActive;
    }

    public void setIsActive(TagStatus isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
