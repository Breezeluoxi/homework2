package wangwei.dao.impl;

import wangwei.dao.UserDao;
import wangwei.model.User;
import wangwei.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement =JDBCUtils.getConnection().prepareStatement("insert into user values(null,?,?,?,?,?,?)");
        setUserInfo(user, statement);
        statement.executeUpdate();
        JDBCUtils.close(connection,statement);
    }

    @Override
    public int getUserCount() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("select count(1) from user");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        JDBCUtils.close(connection,statement,resultSet);
        return count;
    }

    @Override
    public List<User> getUserList(int start, int num) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from user limit ?,?");
        statement.setInt(1,start);
        statement.setInt(2,num);
        ResultSet resultSet = statement.executeQuery();
        List<User> list=new ArrayList<>();
        while (resultSet.next()){
            User user=new User();
            getUserInfo(resultSet, user);
            list.add(user);
        }
        JDBCUtils.close(connection,statement,resultSet);
        return list;
    }

    @Override
    public int deleteUser(int userId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from user where id=?");
        statement.setInt(1,userId);
        int i = statement.executeUpdate();
        JDBCUtils.close(connection,statement);
        return i;
    }

    @Override
    public User queryUser(int id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from user where id=?");
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        User user=new User();
        while (resultSet.next()){
            getUserInfo(resultSet, user);
        }
        JDBCUtils.close(connection,statement,resultSet);
        return user;
    }

    @Override
    public int updateUser(User user) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("update user set name=?,password=?,gender=?,age=?,hobby=?,picUrl=? where id=?");
        setUserInfo(user, statement);
        statement.setInt(7,user.getId());
        int i= statement.executeUpdate();
        JDBCUtils.close(connection,statement);
        return i;
    }
    private void getUserInfo(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setGender(resultSet.getString(4));
        user.setAge(resultSet.getString(5));
        user.setHobby(resultSet.getString(6));
        user.setPicUrl(resultSet.getString(7));
    }
    private void setUserInfo(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1,user.getName());
        statement.setString(2,user.getPassword());
        statement.setString(3,user.getGender());
        statement.setString(4,user.getAge());
        statement.setString(5,user.getHobby());
        statement.setString(6,user.getPicUrl());
    }
}
