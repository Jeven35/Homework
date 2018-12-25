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

    public boolean login(String username,String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSourceFactory.getConnection();
            preparedStatement = connection.prepareStatement( "select * from users where name = ? and password = ?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()){
                user = new User(resultSet.getString("name"),resultSet.getString("password"));
            }
            if (user != null){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void register(String username,String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSourceFactory.getConnection();
            String sql = "insert into users value(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
