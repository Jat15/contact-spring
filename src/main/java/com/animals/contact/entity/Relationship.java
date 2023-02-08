package com.animals.contact.entity;

import jakarta.persistence.*;

@Entity
public class Relationship {
    private String tag;
    @ManyToOne
    @Id
    @JoinColumn(name = "contact_1_id")
    private Contact contact1;

    @ManyToOne
    @Id
    @JoinColumn(name = "contact_2_id")
    private Contact contact2;
}
