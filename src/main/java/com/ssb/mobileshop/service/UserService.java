package com.ssb.mobileshop.service;

import com.ssb.mobileshop.dao.impl.UserDaoImpl;
import com.ssb.mobileshop.model.User;

public class UserService {
    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
    public User RegisterValidation(User user) throws Exception {
        try{  if(user.getName().equals("")){
              System.out.println("User Should Not be Blank");
          }else if(user.getMobileNumber().equals("")){
              System.out.println("Mobile Number Should not be Blank");
          }else if(user.getPassword().equals("")){
              System.out.println("Password Should Not be bLank");
          }else if(user.getConfirmPassword().equals("")){
              System.out.println("Confirm Password Should not be blank");
          }else if(!user.getPassword().equals(user.getConfirmPassword())){
              System.out.println("Password and confirm password should be match");
          }else{
          return user;
       }
   }catch(NullPointerException e){
       System.out.println("Fields should not be blank");
   }
          return null;
       }
       //Save user to Database
       
       public int add(User user) throws Exception {
           return UserDaoImpl.getInstance().save(user);
       }

}
