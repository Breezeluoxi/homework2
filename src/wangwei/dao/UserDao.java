package wangwei.dao;

import wangwei.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public void addUser(User user) throws SQLException;
    public int getUserCount() throws SQLException;
    public List<User> getUserList(int start,int num) throws SQLException;
    public int deleteUser(int userId) throws SQLException;
    public User queryUser(int id) throws SQLException;
    public int updateUser(User user) throws SQLException;
}
