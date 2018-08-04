package com.heven.holt.service;

import com.heven.holt.entity.Staff;

public interface SelfService {
    Staff login(String account,String password);
    void changePassword(Integer id,String password);
}
