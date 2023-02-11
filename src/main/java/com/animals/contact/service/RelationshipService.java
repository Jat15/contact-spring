package com.animals.contact.service;

import com.animals.contact.entity.Contact;
import com.animals.contact.entity.Relationship;
import com.animals.contact.entity.Tag;
import com.animals.contact.repository.ContactRepository;
import com.animals.contact.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelationshipService{
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    RelationshipRepository relationshipRepository;

    public Relationship add(Long contactSrcId,Long contactDestId, Long tagSrcId) {
        Contact contactSrc = new Contact();
        contactSrc.setId(contactSrcId);

        Contact contactDest = new Contact();
        contactDest.setId(contactDestId);

        Tag tagSrc = new Tag();
        tagSrc.setId(tagSrcId);

        Relationship relation = new Relationship(contactSrc, contactDest, tagSrc);

        return relationshipRepository.save(relation);
    }

    public Iterable<Relationship> findByIdSrc(Long id){
        return relationshipRepository.findByContactSrcId(id);
    }

    public  Iterable<Relationship> findByIdDest(Long id){
        return relationshipRepository.findByContactDestId(id);
    }
}
