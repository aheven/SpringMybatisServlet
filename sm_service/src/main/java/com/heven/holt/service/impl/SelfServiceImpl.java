package com.heven.holt.service.impl;

import com.heven.holt.dao.SelfDao;
import com.heven.holt.dao.StaffDao;
import com.heven.holt.entity.Staff;
import com.heven.holt.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("selfService")
public class SelfServiceImpl implements SelfService {
    private final SelfDao selfDao;
    private final StaffDao staffDao;

    @Autowired
    public SelfServiceImpl(SelfDao selfDao,StaffDao staffDao) {
        this.selfDao = selfDao;
        this.staffDao = staffDao;
    }

    public Staff login(String account, String password) {
        Staff staff = selfDao.selectByAccount(account);
        if (staff == null) return null;
        if (staff.getPassword().equals(password)) return staff;
        return null;
    }

    public void changePassword(Integer id, String password) {
        Staff staff = staffDao.selectById(id);
        staff.setPassword(password);
        staffDao.update(staff);
    }
}
