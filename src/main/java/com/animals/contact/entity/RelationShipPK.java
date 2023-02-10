package com.animals.contact.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelationShipPK implements Serializable {
    @Column(name = "contact_src_id")
    private Long contactSrcId;
    @Column(name="contact_dest_id")
    private Long contactDestId;

    public RelationShipPK() {
    }

    public RelationShipPK(Long contactSrcId, Long contactDestId) {
        this.contactSrcId = contactSrcId;
        this.contactDestId = contactDestId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationShipPK that = (RelationShipPK) o;
        return Objects.equals(contactSrcId, that.contactSrcId) && Objects.equals(contactDestId, that.contactDestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactSrcId, contactDestId);
    }
}
