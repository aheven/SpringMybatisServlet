package com.heven.holt;

import com.heven.holt.dao.PersonDao;
import com.heven.holt.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PersonTest {
    @Autowired
    private PersonDao personDao;

    @Test
    public void test1(){
        Person person = new Person();
        person.setUsername("zhangsan");
        person.setGender("f");
        Person person1 = personDao.getPersonByNameAndGender(person);
        System.out.println(person1);
    }
}
