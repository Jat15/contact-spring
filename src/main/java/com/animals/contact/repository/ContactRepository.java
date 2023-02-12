package com.animals.contact.repository;

import com.animals.contact.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
     Iterable<Contact> findAllByLastnameContainingAndUserIdOrFirstnameContainingAndUserIdOrTelContainingAndUserIdOrEmailContainingAndUserId(String lastname, Long user_id, String firstname, Long user_id2, String tel, Long user_id3, String email, Long user_id4);
}
