package com.animals.contact.entity;

import jakarta.persistence.*;

@Entity
public class Relationship {
    @OneToOne
    private Tag tagSrc;
    @ManyToOne
    @Id
    @JoinColumn(name = "contact_src")
    private Contact contactSrc;

    @ManyToOne
    @Id
    @JoinColumn(name = "contact_dest")
    private Contact contactDest;
}
