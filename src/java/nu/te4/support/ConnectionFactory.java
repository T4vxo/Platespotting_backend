package nu.te4.support;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author DanLun2
 */
public class ConnectionFactory {

    public static Connection make(String database) throws Exception {
        String username = "";
        String password = "";
        String url = "";
        try {
            JsonReader jsonReader = Json.createReader(ConnectionFactory.class.getResourceAsStream("settings.json"));
            JsonObject settings = jsonReader.readObject();
            jsonReader.close();
            username = settings.getString("username");
            password = settings.getString("password");
            url = settings.getString("url");
        } catch (Exception e) {
            System.out.println("Error read json: " + e.getMessage());
        }
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection(url + database, username, password);
        return connection;
    }

}
