package com.animals.contact.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelationShipPK implements Serializable {
    @Column(name = "contact_src_id")
    private Long contactSrcId;
    @Column(name="contact_dest_id")
    private Long contactDestId;

    public Long getContactSrcId() {
        return contactSrcId;
    }

    public void setContactSrcId(Long contactSrcId) {
        this.contactSrcId = contactSrcId;
    }

    public Long getContactDestId() {
        return contactDestId;
    }

    public void setContactDestId(Long contactDestId) {
        this.contactDestId = contactDestId;
    }
}
