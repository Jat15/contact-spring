package com.animals.contact.repository;

import com.animals.contact.entity.Contact;
import com.animals.contact.entity.RelationShipPK;
import com.animals.contact.entity.Relationship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends CrudRepository<Relationship, RelationShipPK> {
    Contact findByIdContactSrcId(Long id);
    Contact findByIdContactDestId(Long id);
}
