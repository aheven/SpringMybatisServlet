package com.heven.holt;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heven.holt.dao.PersonDao;
import com.heven.holt.entity.Person;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        PersonDao mapper = sqlSession.getMapper(PersonDao.class);
        for (int i = 0; i < 2500; i++) {
            mapper.updatePerson(new Person(i, "tom", "tom@imooc.com", "d"));
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test2() {
        Page<Object> page = PageHelper.startPage(11, 20);
        List<Person> persons = personDao.getAll();
        PageInfo<Person> pageInfo = new PageInfo<Person>(persons);
        for (Person person :
                persons) {
            System.out.println(person);
        }
        System.out.println(pageInfo);
    }
}
