package nu.te4.support;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DanLun2
 */
public class ConnectionFactory {

    public static String USERNAME = "root", PASSWORD = "";
    public static Connection make(String database) throws Exception {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+database, USERNAME,PASSWORD);
            return connection;    
    }
    
    
}
