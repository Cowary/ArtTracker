package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.Person;
import com.ruderu.mediarecord.model.shiki.PersonModel;
import com.ruderu.mediarecord.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonCrud {

    @Autowired
    PersonRepo personRepo;

    public Person createOrGetByName(PersonModel personModel) {
        return personRepo.findByNameEn(personModel.getName()).orElseGet(() -> {
            Person person = new Person(personModel.getName(), personModel.getRussian(), personModel.getId());
            personRepo.save(person);
            return person;
        });
    }
}
