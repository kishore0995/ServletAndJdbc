package com.ssb.mobileshop.service;


import java.sql.SQLException;

import com.ssb.mobileshop.dao.impl.UserDaoImpl;
import com.ssb.mobileshop.model.User;

public class LoginService {
    private static LoginService loginService;

    public static LoginService getInstance() {
        if (loginService == null) {
            loginService = new LoginService();
        }
        return loginService;
    }

    public User loginValidation(String mobileNumber, String password) {
        try {
            User login = UserDaoImpl.getInstance().findByMobileNumber(mobileNumber, password);
            if(login.getMobileNumber().equals(mobileNumber)&&login.getPassword().equals(password)){
                return login;
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
       
   }
}
