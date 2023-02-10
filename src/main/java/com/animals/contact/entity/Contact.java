package com.animals.contact.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastname;
    private String firstname;
    private String email;
    private String tel;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy="contactSrc")
    private List<Relationship> relationshipsSrc;

    @OneToMany(mappedBy="contactDest")
    private List<Relationship> relationshipsDest;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Relationship> getRelationshipsSrc() {
        return relationshipsSrc;
    }

    public void setRelationshipsSrc(List<Relationship> relationshipsSrc) {
        this.relationshipsSrc = relationshipsSrc;
    }

    public List<Relationship> getRelationshipsDest() {
        return relationshipsDest;
    }

    public void setRelationshipsDest(List<Relationship> relationshipsDest) {
        this.relationshipsDest = relationshipsDest;
    }
}
