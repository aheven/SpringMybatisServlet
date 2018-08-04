package com.heven.holt.dao;

import com.heven.holt.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("personDao")
public interface PersonDao {
    List<Person> getAll();

    void delete(Integer id);

    Person getPersonByNameAndGender(Person person);
}
