package com.animals.contact.service;

import com.animals.contact.entity.Tag;
import com.animals.contact.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;
    public Iterable<Tag> all(){
        return tagRepository.findAll();
    }
}
