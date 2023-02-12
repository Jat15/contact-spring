package com.animals.contact.repository;

import com.animals.contact.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
     Iterable<Contact> findAllByLastnameContainingOrFirstnameContainingOrTelContainingOrEmailContaining(String lastname, String firstname, String tel, String email);
}
