package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jeven on 2018/12/24.
 */
public class MyDataSource {

    private static DataSource source;

    private static void init() throws NamingException {
        Context context = new InitialContext();
        source = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
    }

    Connection getConnection() {
        try {
            if (source == null)
                init();
        } catch (NamingException e) {
            return null;
        }
        Connection connection = null;
        try {
            connection = source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection; // connection may be null
    }

}
