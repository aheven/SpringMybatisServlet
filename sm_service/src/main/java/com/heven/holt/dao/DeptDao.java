package com.heven.holt.dao;

import com.heven.holt.entity.Dept;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptDao {
    Dept selectById(Integer id);
}
