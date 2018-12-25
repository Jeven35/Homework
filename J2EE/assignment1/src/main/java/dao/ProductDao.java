package dao;

import po.Good;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jeven on 2018/12/24.
 */
public class ProductDao {
    private DataSourceFactory dataSourceFactory;

    public ProductDao(){
        dataSourceFactory = new DataSourceFactory();
    }

    public ArrayList<Good> getGoos(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSourceFactory.getConnection();
            String sql = "select * from goods";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Good> goods = new ArrayList<>();
            Good good = null;
            while (resultSet.next()){
                good = new Good(resultSet.getString("gid"),resultSet.getString("gname"),resultSet.getDouble("price"));
                goods.add(good);
            }
            return goods;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
