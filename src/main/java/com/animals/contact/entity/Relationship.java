package com.animals.contact.entity;

import jakarta.persistence.*;

@Entity
public class Relationship {
    @EmbeddedId
    private RelationShipPK id;

    @ManyToOne
    @JoinColumn(name = "contact_src")
    @MapsId("contactSrcId")
    private Contact contactSrc;

    @ManyToOne
    @JoinColumn(name = "contact_dest")
    @MapsId("contactDestId")
    private Contact contactDest;

    @OneToOne
    private Tag tagSrc;

    public Relationship(RelationShipPK id, Contact contactSrc, Contact contactDest, Tag tagSrc) {
        this.id = id;
        this.contactSrc = contactSrc;
        this.contactDest = contactDest;
        this.tagSrc = tagSrc;
    }

    public Relationship() {

    }

    public RelationShipPK getId() {
        return id;
    }

    public void setId(RelationShipPK id) {
        this.id = id;
    }

    public Contact getContactSrc() {
        return contactSrc;
    }

    public void setContactSrc(Contact contactSrc) {
        this.contactSrc = contactSrc;
    }

    public Contact getContactDest() {
        return contactDest;
    }

    public void setContactDest(Contact contactDest) {
        this.contactDest = contactDest;
    }

    public Tag getTagSrc() {
        return tagSrc;
    }

    public void setTagSrc(Tag tagSrc) {
        this.tagSrc = tagSrc;
    }
}
