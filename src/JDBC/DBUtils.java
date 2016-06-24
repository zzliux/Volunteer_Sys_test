package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * @author md
 */
public class DBUtils {
    public final static String URL;
    public final static String USERNAME;
    public final static String PASSWORD;
    public final static String DRIVER;

    public static ResourceBundle rb = ResourceBundle .getBundle("JDBC.db-config");

    private DBUtils() {
    }

    static {
        URL = rb.getString("jdbc.url");
        USERNAME = rb.getString("jdbc.username");
        DRIVER = rb.getString("jdbc.driver");
        PASSWORD = rb.getString("jdbc.password");
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("用户名或密码错误！");
        }
        return conn;
    }
}
