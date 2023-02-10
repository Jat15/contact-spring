package com.animals.contact.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RelationShipPK implements Serializable {
    @Column(name = "contact_src_id")
    private Long contactSrcId;
    @Column(name="contact_dest_id")
    private Long contactDestId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationShipPK that = (RelationShipPK) o;

        if (!getContactSrcId().equals(that.getContactSrcId())) return false;
        return getContactDestId().equals(that.getContactDestId());
    }

    @Override
    public int hashCode() {
        int result = getContactSrcId().hashCode();
        result = 31 * result + getContactDestId().hashCode();
        return result;
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
}
