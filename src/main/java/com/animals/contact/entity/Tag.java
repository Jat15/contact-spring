package com.animals.contact.entity;

import jakarta.persistence.*;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String male;
    private String female;
    private String neutral;
    @OneToOne
    private Tag tagDest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    public String getNeutral() {
        String resultNeutral = neutral;

        if ( resultNeutral == null ) {
            resultNeutral =  getFemale() + " / " + getMale();
        }

        return resultNeutral;
    }

    public void setNeutral(String neutral) {this.neutral = neutral;}

    public Tag getTagDest() {
        return tagDest;
    }

    public void setTagDest(Tag tagDest) {
        this.tagDest = tagDest;
    }
}
