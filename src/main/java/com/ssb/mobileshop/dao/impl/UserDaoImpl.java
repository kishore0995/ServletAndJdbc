package com.ssb.mobileshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssb.mobileshop.dao.UserDao;
import com.ssb.mobileshop.model.User;
import com.ssb.mobileshop.util.JdbcConnection;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl userDaoImpl;
    //singleton instance for UserDao
    public static UserDaoImpl getInstance() {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return userDaoImpl;
    }

    //Save User to Database
    @Override
    public int save(User user) throws Exception {
        String sql = "Insert into user(name,mobile,password,confirm_password,role_id)values(?,?,?,?,?)";
        Connection con = JdbcConnection.getConnection();
        int insertStatus = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getMobileNumber());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getConfirmPassword());
            ps.setInt(5,user.getRoleId());
            insertStatus = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.close();
        }
        return insertStatus;
    }

    //Get user by Mobile Number
    @Override
    public User findByMobileNumber(String mobile, String password) throws SQLException {
        String sql = "select * from user where mobile="+mobile+" && password='"+ password+"'";
        Connection connection = JdbcConnection.getConnection();
        User login = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                login.setMobileNumber(rs.getString("mobile"));
                login.setPassword(rs.getString("password"));
                login.setName(rs.getString("name"));
                login.setRoleId(rs.getInt("role_id"));
            }
        } catch (Exception exception) {
            System.out.println(exception);
           return null;
        } finally {
            connection.close();
        }
        return login;
    }
}
