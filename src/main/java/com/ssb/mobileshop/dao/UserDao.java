package com.ssb.mobileshop.dao;

import com.ssb.mobileshop.model.User;

public interface UserDao {
    int save(User user)throws Exception;
    User findByMobileNumber(String Mobile,String password)throws Exception;
}
