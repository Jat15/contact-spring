package com.animals.contact.repository;

import com.animals.contact.entity.RelationShipPK;
import com.animals.contact.entity.Relationship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends CrudRepository<Relationship, RelationShipPK> {
    Iterable<Relationship> findByContactSrcId(Long id);
    Iterable<Relationship> findByContactDestId(Long id);
    void deleteAllByContactDest_IdOrContactSrc_Id(Long contactDest_id, Long contactSrc_id);
}
