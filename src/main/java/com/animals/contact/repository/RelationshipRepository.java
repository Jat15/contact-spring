package com.animals.contact.repository;

import com.animals.contact.entity.Relationship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends CrudRepository<Relationship, Long> {

}
