package dao;

import po.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jeven on 2018/12/24.
 */
public class UserDao {
    private DataSourceFactory dataSourceFactory;

    public UserDao(){
        dataSourceFactory = new DataSourceFactory();
    }

    public User getUser(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSourceFactory.getConnection();
            preparedStatement = connection.prepareStatement( "select * from users");
            resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()){
                user = new User(resultSet.getString("name"),resultSet.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
