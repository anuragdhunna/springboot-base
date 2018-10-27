package com.springboot.base.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Protocol {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int protocolId;

    @Column(length = 200)
    private String title;

    private Timestamp createdOn;

    private Timestamp effectivityDate;


    public int getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(int protocolId) {
        this.protocolId = protocolId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getEffectivityDate() {
        return effectivityDate;
    }

    public void setEffectivityDate(Timestamp effectivityDate) {
        this.effectivityDate = effectivityDate;
    }
}
