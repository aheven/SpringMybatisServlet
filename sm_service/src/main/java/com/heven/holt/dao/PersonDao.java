package com.heven.holt.dao;

import com.heven.holt.entity.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository("personDao")
public interface PersonDao {
    List<Person> getAll();

    void delete(Integer id);

    Person getPersonByNameAndGender(Person person);

    Person getPersonByNameAndGender(@Param("username") String username, @Param("gender") String gender);

    Person getPersonByCollection(Collection list);

    List<Person> getPersonsByIds(@Param("ids") int[] ids);

    int addPerson(Person person);

    int addPersons(@Param("persons") List<Person> persons);

    int updatePerson(Person person);
}
